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

    // 신체기록 서비스
    public UserBodyInfoDTO recordeUserBodyInfo(UserBodyInfoDTO UserBodyInfoDTO) {

        // UserInfo 엔티티 먼저 확인 및 저장
        String userid = UserBodyInfoDTO.getUserid(); // DTO에서 userid 가져오기
        UserInfo foundUserInfo = RepoUserInfo.findByUserid(userid);
        if (foundUserInfo == null) {
            throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
        }
        // UserBodyInfoDTO.setUserInfo(foundUserInfo);

        double fatMass = UserBodyInfoDTO.getWeight() * (UserBodyInfoDTO.getFatpercentage() / 100); // 체지방량(fat mass) 계산 공식, 몸무게x (체지방률 / 100)
        double heightInMeters = UserBodyInfoDTO.getHeight() / 100.0;
        double bmi = UserBodyInfoDTO.getWeight() / (heightInMeters * heightInMeters);  // 키를 cm에서 m 단위로 변환 후, 몸무게를 나눠서 BMI 계산
        double leanmass = UserBodyInfoDTO.getWeight() - fatMass; // 몸무게에서 체지방량을 빼서 제지방량 계산 (몸무게 - 체지방량)
        LocalDate birth = RepoUserInfo.getUserBirthById(userid);

        UserBodyInfoDTO.setAge(calAge(birth));
        UserBodyInfoDTO.setSex(RepoUserInfo.getUserSexById(userid));
        UserBodyInfoDTO.setLeanmass(Math.round(leanmass * 100.0) / 100.0);
        UserBodyInfoDTO.setFatmass(Math.round(fatMass * 100.0) / 100.0);
        UserBodyInfoDTO.setBmi(Math.round(bmi * 100.0) / 100.0);
        UserBodyInfoDTO.setDate(new Date());

        // 성별에 따른 inbodyScore 계산
        double inbodyScore;
        if (UserBodyInfoDTO.getSex() == 1) { // 남성
            inbodyScore = (100 - UserBodyInfoDTO.getFatpercentage()) + (UserBodyInfoDTO.getLeanmass() * 0.2); // 체지방률이 낮을수록 점수가 높아짐, 제지방량(Lean Mass)이 많을수록 추가 점수 부여
        } else { // 여성
            inbodyScore = (100 - UserBodyInfoDTO.getFatpercentage()) * 0.8 + (UserBodyInfoDTO.getWeight() * 0.1); //체지방률의 영향을 줄이고, 몸무게를 일부 반영, 남성과 다르게 제지방량을 고려하지 않음

        }
        UserBodyInfoDTO.setInbodyScore(Math.round(inbodyScore * 100.0) / 100.0);

        ScoreRankService.saveToScoreRank(UserBodyInfoDTO);

        RepoUserBodyInfo.save(convertToEntity(UserBodyInfoDTO));

        return UserBodyInfoDTO;
    }

    // 최근 신체 기록 가져오기 최근 5개만 가져옴
    public List<UserBodyInfoDTO> getRecentUserBodyRecords(String userid) {
        List<UserBodyInfo> records = RepoUserBodyInfo.findTop5ByUserInfo_UseridOrderByDateDesc(userid);
        return records.stream()
                .map(UserBodyInfo -> new UserBodyInfoDTO(UserBodyInfo))
                .collect(Collectors.toList());
    }

    // 사용자의 생년월일을 기반으로 현재 연도와 비교하여 나이를 반환합니다.
    private int calAge(LocalDate birth) {
        int age = 0;

        if (birth != null) {
            LocalDate currentDate = LocalDate.now();
            age = currentDate.getYear() - birth.getYear() - (currentDate.getDayOfYear() < birth.getDayOfYear() ? 1 : 0);
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
