package com.example.demo.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.example.demo.Entity.ScoreRankFemale;
import com.example.demo.Entity.ScoreRankMale;
import com.example.demo.Repo.ScoreRankFemaleRepository;
import com.example.demo.Repo.ScoreRankMaleRepository;
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

    @Autowired
    private ScoreRankMaleRepository scoreRankMaleRepository;

    @Autowired
    private ScoreRankFemaleRepository scoreRankFemaleRepository;

    public UserBodyInfo recordeUserBodyInfo(UserBodyInfo UserBodyInfo) {
        double fatMass = UserBodyInfo.getWeight() * (UserBodyInfo.getFatpercentage() / 100);
        // BMI 계산 몸무게kg / (키(m) * 키(m)))
        double heightInMeters = UserBodyInfo.getHeight() / 100.0; // 키 cm -> m 변환
        double bmi = UserBodyInfo.getWeight() / (heightInMeters * heightInMeters); // BMI 계산
        double inbodyScore = (100 - UserBodyInfo.getFatpercentage()) + (UserBodyInfo.getWeight() * 0.1); // 인바디 점수 나중에
        double leanmass = UserBodyInfo.getWeight() - fatMass; // 제지방량 계산
        LocalDate birth = RepoUserInfo.getUserBirthById(UserBodyInfo.getUserid()); // 생년월일 가져오기

        UserBodyInfo.setAge(calAge(birth)); // 넘길어서 메소드 만듬
        UserBodyInfo.setSex(RepoUserInfo.getUserSexById(UserBodyInfo.getUserid())); // 성별 가져오기
        UserBodyInfo.setLeanmass(Math.round(leanmass * 100.0) / 100.0); // 제지방량 반올림
        UserBodyInfo.setFatMass(Math.round(fatMass * 100.0) / 100.0); // 체지방량 반올림
        UserBodyInfo.setBmi(Math.round(bmi * 100.0) / 100.0); // BMI 반올림
        UserBodyInfo.setInbodyScore(Math.round(inbodyScore * 100.0) / 100.0); // 인바디 점수 반올림
        UserBodyInfo.setDate(new Date()); // 기록시간 나중에 그래프 만들때 알아서 변경해서 사용해주세요

        // 신체 정보 저장
        UserBodyInfo savedInfo = RepoUserBodyInfo.save(UserBodyInfo);

        // 점수 랭킹 저장 (남성/여성 분리)
        saveToScoreRank(UserBodyInfo, (int) inbodyScore);

        return savedInfo;
    }


    //사용자 정보를 기반으로 점수를 저장하는 메서드입니다.
    //성별(sex)에 따라 score_rank_male 또는 score_rank_female 테이블에 저장합니다.
    private void saveToScoreRank(UserBodyInfo userBodyInfo, int score) {
        if (userBodyInfo.getSex() == 1) { // 남성
            ScoreRankMale rankMale = new ScoreRankMale(
                    userBodyInfo.getSex(),
                    userBodyInfo.getAge(),
                    userBodyInfo.getHeight(),
                    userBodyInfo.getWeight(),
                    userBodyInfo.getLeanmass(),
                    userBodyInfo.getFatMass(),
                    (float) userBodyInfo.getFatpercentage(), // double → float 변환
                    score,
                    userBodyInfo.getUserid()
            );
            scoreRankMaleRepository.save(rankMale);
        } else if (userBodyInfo.getSex() == 2) { // 여성
            ScoreRankFemale rankFemale = new ScoreRankFemale(
                    userBodyInfo.getSex(),
                    userBodyInfo.getAge(),
                    userBodyInfo.getHeight(),
                    userBodyInfo.getWeight(),
                    userBodyInfo.getLeanmass(),
                    userBodyInfo.getFatMass(),
                    (float) userBodyInfo.getFatpercentage(), // double > float 변환
                    score,
                    userBodyInfo.getUserid()
            );
            scoreRankFemaleRepository.save(rankFemale);
        }
    }

    //특정 사용자의 최근 신체 정보 기록을 가져옴
    public List<UserBodyInfo> getRecentUserBodyRecords(String userid) {
        return RepoUserBodyInfo.findRecentByUserid(userid);
    }

    //사용자의 생년월일을 기반으로 현재 연도와 비교하여 나이를 반환합니다.
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
