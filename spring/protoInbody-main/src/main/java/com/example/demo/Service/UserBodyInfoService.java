package com.example.demo.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserBodyInfo;
import com.example.demo.Repo.RepoUserBodyInfo;
import com.example.demo.Repo.RepoUserInfo;

@Service
public class UserBodyInfoService {

    @Autowired
    private RepoUserBodyInfo RepoUserBodyInfo;

    @Autowired
    private RepoUserInfo RepoUserInfo;

    public UserBodyInfo recordeUserBodyInfo(UserBodyInfo UserBodyInfo) {
        double fatMass = UserBodyInfo.getWeight() * (UserBodyInfo.getFatpercentage() / 100);
        // BMI 계산 몸무게kg / (키(m) * 키(m)))
        double heightInMeters = UserBodyInfo.getHeight() / 100.0; // 키 cm -> m 변환
        double bmi = UserBodyInfo.getWeight() / (heightInMeters * heightInMeters); // bmi 계산
        double inbodyScore = (100 - UserBodyInfo.getFatpercentage()) + (UserBodyInfo.getWeight() * 0.1); // 인바디 점수 나중에
        double leanmass = UserBodyInfo.getWeight() - fatMass;// 제지방량 계산
        LocalDate birth = RepoUserInfo.getUserBirthById(UserBodyInfo.getUserid()); // 생년월일 가져오기

        UserBodyInfo.setAge(calAge(birth)); // 넘길어서 메소드 만듬
        UserBodyInfo.setSex(RepoUserInfo.getUserSexById(UserBodyInfo.getUserid()));
        UserBodyInfo.setLeanmass(Math.round(leanmass * 100.0 / 100.0));// 제지방량
        UserBodyInfo.setFatMass(Math.round(fatMass * 100.0) / 100.0); // 체지방량
        UserBodyInfo.setBmi(Math.round(bmi * 100.0) / 100.0); // bmi
        UserBodyInfo.setInbodyScore(Math.round(inbodyScore * 100.0) / 100.0); // 인바디 점수
        UserBodyInfo.setDate(new Date()); // 기록시간 나중에 그래프 만들때 알아서 변경해서 사용해주세요

        return RepoUserBodyInfo.save(UserBodyInfo);
    }

    public List<UserBodyInfo> getRecentUserBodyRecords(String userid) {
        return RepoUserBodyInfo.findRecentByUserid(userid);
    }

    // 나이 계산하는 메소드 입니다
    private int calAge(LocalDate birth) {
        int age = 0;

        if (birth != null) {
            LocalDate currentDate = LocalDate.now();
            age = currentDate.getYear() - birth.getYear() -
                    (currentDate.getDayOfYear() < birth.getDayOfYear() ? 1 : 0);
        }

        return age;
    }

}
