package com.example.demo.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.DTO.UserBodyInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserBodyInfo;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Repo.RepoUserBodyInfo;
import com.example.demo.Repo.RepoUserInfo;

import org.modelmapper.ModelMapper;

@Service
public class UserBodyInfoService {

    @Autowired
    private RepoUserBodyInfo RepoUserBodyInfo;

    @Autowired
    private RepoUserInfo RepoUserInfo;

    @Autowired
    private ScoreRankService ScoreRankService;

    private ModelMapper modelMapper = new ModelMapper();

    public UserBodyInfoDTO recordeUserBodyInfo(UserBodyInfoDTO UserBodyInfoDTO) {

        // UserInfo 엔티티 먼저 확인 및 저장
        String userid = UserBodyInfoDTO.getUserid(); // DTO에서 userid 가져오기
        UserInfo foundUserInfo = RepoUserInfo.findByUserid(userid);
        if (foundUserInfo == null) {
            throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
        }
        // UserBodyInfoDTO.setUserInfo(foundUserInfo);

        double fatMass = UserBodyInfoDTO.getWeight() * (UserBodyInfoDTO.getFatpercentage() / 100);
        double heightInMeters = UserBodyInfoDTO.getHeight() / 100.0;
        double bmi = UserBodyInfoDTO.getWeight() / (heightInMeters * heightInMeters);
        double inbodyScore = (100 - UserBodyInfoDTO.getFatpercentage()) + (UserBodyInfoDTO.getWeight() * 0.1);
        double leanmass = UserBodyInfoDTO.getWeight() - fatMass;
        LocalDate birth = RepoUserInfo.getUserBirthById(userid);

        UserBodyInfoDTO.setAge(calAge(birth));
        UserBodyInfoDTO.setSex(RepoUserInfo.getUserSexById(userid));
        UserBodyInfoDTO.setLeanmass(Math.round(leanmass * 100.0) / 100.0);
        UserBodyInfoDTO.setFatmass(Math.round(fatMass * 100.0) / 100.0);
        UserBodyInfoDTO.setBmi(Math.round(bmi * 100.0) / 100.0);
        UserBodyInfoDTO.setInbodyScore(Math.round(inbodyScore * 100.0) / 100.0);
        UserBodyInfoDTO.setDate(new Date());

        ScoreRankService.saveToScoreRank(UserBodyInfoDTO);

        RepoUserBodyInfo.save(convertToEntity(UserBodyInfoDTO));

        return UserBodyInfoDTO;
    }

    public List<UserBodyInfoDTO> getRecentUserBodyRecords(String userid) {
        List<UserBodyInfo> records = RepoUserBodyInfo.findByUserInfo_UseridOrderByDateDesc(userid);
        return records.stream()
                .map(UserBodyInfo -> new UserBodyInfoDTO(UserBodyInfo))
                .collect(Collectors.toList());
    }

    // 사용자의 생년월일을 기반으로 현재 연도와 비교하여 나이를 반환합니다.
    private int calAge(LocalDate birth) {
        int age = 0;

        if (birth != null) {
            LocalDate currentDate = LocalDate.now();
            age = currentDate.getYear() - birth.getYear() -
                    (currentDate.getDayOfYear() < birth.getDayOfYear() ? 1 : 0);
        }

        return age;
    }

    private UserBodyInfo convertToEntity(UserBodyInfoDTO UserBodyInfoDTO) {
        UserInfo UserInfo = RepoUserInfo.findByUserid(UserBodyInfoDTO.getUserid());
        UserBodyInfo UserBodyInfo = modelMapper.map(UserBodyInfoDTO, UserBodyInfo.class);
        UserBodyInfo.setUserInfo(UserInfo);
        return UserBodyInfo;

    }

}
