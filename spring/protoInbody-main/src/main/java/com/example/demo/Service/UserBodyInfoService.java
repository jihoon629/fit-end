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

@Service
public class UserBodyInfoService {

    @Autowired
    private RepoUserBodyInfo RepoUserBodyInfo;

    @Autowired
    private RepoUserInfo RepoUserInfo;

    @Autowired
    private ScoreRankService ScoreRankService;

    public UserBodyInfoDTO recordeUserBodyInfo(UserBodyInfoDTO userBodyInfoDTO) {
        System.out.println(userBodyInfoDTO);

        UserBodyInfo userBodyInfo = convertToEntity(userBodyInfoDTO);

        // UserInfo 엔티티 먼저 확인 및 저장
        String userid = userBodyInfo.getUserInfo().getUserid(); // DTO에서 userid 가져오기
        UserInfo foundUserInfo = RepoUserInfo.findByUserid(userid);
        if (foundUserInfo == null) {
            throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
        }
        userBodyInfo.setUserInfo(foundUserInfo);

        double fatMass = userBodyInfo.getWeight() * (userBodyInfo.getFatpercentage() / 100);
        double heightInMeters = userBodyInfo.getHeight() / 100.0;
        double bmi = userBodyInfo.getWeight() / (heightInMeters * heightInMeters);
        double inbodyScore = (100 - userBodyInfo.getFatpercentage()) + (userBodyInfo.getWeight() * 0.1);
        double leanmass = userBodyInfo.getWeight() - fatMass;
        LocalDate birth = RepoUserInfo.getUserBirthById(userid);

        userBodyInfo.setAge(calAge(birth));
        userBodyInfo.setSex(RepoUserInfo.getUserSexById(userid));
        userBodyInfo.setLeanmass(Math.round(leanmass * 100.0) / 100.0);
        userBodyInfo.setFatMass(Math.round(fatMass * 100.0) / 100.0);
        userBodyInfo.setBmi(Math.round(bmi * 100.0) / 100.0);
        userBodyInfo.setInbodyScore(Math.round(inbodyScore * 100.0) / 100.0);
        userBodyInfo.setDate(new Date());

        UserBodyInfo savedInfo = RepoUserBodyInfo.save(userBodyInfo);

        ScoreRankService.saveToScoreRank(userBodyInfo, (int) inbodyScore);

        return convertToDTO(savedInfo);
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
        UserInfo userInfo = RepoUserInfo.findByUserid(UserBodyInfoDTO.getUserid());
        return new UserBodyInfo(
                userInfo,
                UserBodyInfoDTO.getId(),
                UserBodyInfoDTO.getHeight(),
                UserBodyInfoDTO.getWeight(),
                UserBodyInfoDTO.getFatpercentage(),
                UserBodyInfoDTO.getFatmass(),
                UserBodyInfoDTO.getLeanmass(),
                UserBodyInfoDTO.getBmi(),
                UserBodyInfoDTO.getInbodyScore(),
                UserBodyInfoDTO.getDate(),
                UserBodyInfoDTO.getSex(),
                UserBodyInfoDTO.getAge());
    }

    private UserBodyInfoDTO convertToDTO(UserBodyInfo userBodyInfo) {
        return new UserBodyInfoDTO(
                userBodyInfo.getId(),
                userBodyInfo.getUserInfo().getUserid(),
                userBodyInfo.getHeight(),
                userBodyInfo.getWeight(),
                userBodyInfo.getFatpercentage(),
                userBodyInfo.getFatMass(),
                userBodyInfo.getLeanmass(),
                userBodyInfo.getBmi(),
                userBodyInfo.getInbodyScore(),
                userBodyInfo.getDate(),
                userBodyInfo.getSex(),
                userBodyInfo.getAge());
    }
}
