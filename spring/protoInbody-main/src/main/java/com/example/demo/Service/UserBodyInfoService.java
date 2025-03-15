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
import com.example.demo.Service.Utile.EntityConversionService;

@Service
public class UserBodyInfoService {

    @Autowired
    private RepoUserBodyInfo RepoUserBodyInfo;

    @Autowired
    private RepoUserInfo RepoUserInfo;

    @Autowired
    EntityConversionService EntityConversionService;

    // 신체기록 서비스
    public UserBodyInfoDTO recordeUserBodyInfo(UserBodyInfoDTO UserBodyInfoDTO) {
        // UserInfo 엔티티 먼저 확인 및 저장
        String userid = UserBodyInfoDTO.getUserid(); // DTO에서 userid 가져오기
        UserInfo foundUserInfo = RepoUserInfo.findByUserid(userid);
        if (foundUserInfo == null) {
            throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
        }

        // 키,몸무게,체지방률 가져오기
        double heightInMeters = UserBodyInfoDTO.getHeight() / 100.0; // cm -> m 변환
        double fatMass = UserBodyInfoDTO.getWeight() * (UserBodyInfoDTO.getFatpercentage() / 100.0); // 체지방량(FatMass)
                                                                                                     // =몸무게 x 체지방률
        double leanMass = UserBodyInfoDTO.getWeight() - fatMass; // 제지방량(LeanMass) = 몸무게 - 체지방량
        double bmi = UserBodyInfoDTO.getWeight() / (heightInMeters * heightInMeters); // BMI = 몸무게 ÷ (키(m)²)
        LocalDate birth = RepoUserInfo.getUserBirthById(userid); // DB에서 생년월일 불러오기

        // 나이 및 성별 정보 설정
        UserBodyInfoDTO.setAge(calAge(birth)); // 나이 게산후 DTO 저장
        UserBodyInfoDTO.setSex(RepoUserInfo.getUserSexById(userid)); // 성별 가져오기
        UserBodyInfoDTO.setLeanmass(Math.round(leanMass * 100.0) / 100.0);
        UserBodyInfoDTO.setFatmass(Math.round(fatMass * 100.0) / 100.0);
        UserBodyInfoDTO.setBmi(Math.round(bmi * 100.0) / 100.0);
        UserBodyInfoDTO.setDate(new Date());

        // 성별에 따른 inbodyScore 계산
        // DTO에 인바디 점수 저장
        UserBodyInfoDTO.setInbodyScore(calInbodyScore(UserBodyInfoDTO, heightInMeters));

        // 데이터베이스에 저장
        RepoUserBodyInfo.save(EntityConversionService.convertToEntity(UserBodyInfoDTO, UserBodyInfo.class));

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

    // 성별에 따른 inbodyScore 계산
    private double calInbodyScore(UserBodyInfoDTO UserBodyInfoDTO, double heightInMeters) {
        double inbodyScore;
        if (UserBodyInfoDTO.getSex() == 1) { // 남성
            inbodyScore = Math.round(UserBodyInfoDTO.getLeanmass() -
                    ((Math.pow(heightInMeters, 2)) * 22 * 0.85) + 80);
        } else { // 여성
            inbodyScore = Math.round(UserBodyInfoDTO.getLeanmass() -
                    ((Math.pow(heightInMeters, 2)) * 21.5 * 0.77) + 80);
        }
        return inbodyScore;
    }

    public List<UserBodyInfoDTO> getUserBodyInfoByAge(int age) {
        List<UserBodyInfo> userBodyInfos = RepoUserBodyInfo.findByAge(age);
        return userBodyInfos.stream()
                .map(UserBodyInfoDTO::new)
                .collect(Collectors.toList());
    }
}
