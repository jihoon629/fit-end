package com.example.demo.Service;

import com.example.demo.Repo.RepoScoreRankFemale;
import com.example.demo.Repo.RepoScoreRankMale;
import com.example.demo.Entity.ScoreRankMale;
import com.example.demo.Entity.UserBodyInfo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ScoreRankFemaleDTO;
import com.example.demo.DTO.ScoreRankMaleDTO;
import com.example.demo.Entity.ScoreRankFemale;

@Service
public class ScoreRankService {

    @Autowired
    private RepoScoreRankMale RepoScoreRankMale;
    @Autowired
    private RepoScoreRankFemale RepoScoreRankFemale;

    @Autowired
    private RepoScoreRankMale scoreRankMaleRepository;

    @Autowired
    private RepoScoreRankFemale scoreRankFemaleRepository;

    public List<ScoreRankMaleDTO> showRankMale() {
        List<ScoreRankMale> scoreRankMales = RepoScoreRankMale.findAllByOrderByScoreDesc();
        return scoreRankMales.stream()
                .map(scoreRankMale -> new ScoreRankMaleDTO(scoreRankMale))
                .collect(Collectors.toList());
    }

    public List<ScoreRankFemaleDTO> showRankFemale() {
        List<ScoreRankFemale> scoreRankFemales = RepoScoreRankFemale.findAllByOrderByScoreDesc();
        return scoreRankFemales.stream()
                .map(scoreRankFemale -> new ScoreRankFemaleDTO(scoreRankFemale))
                .collect(Collectors.toList());
    }

    // 사용자 정보를 기반으로 점수를 저장하는 메서드입니다.
    // 성별(sex)에 따라 score_rank_male 또는 score_rank_female 테이블에 저장합니다.
    public void saveToScoreRank(UserBodyInfo userBodyInfo, int score) {

        if (userBodyInfo.getSex() == 1) { // 남성
            ScoreRankMale rankMale = scoreRankMaleRepository
                    .findByUserInfo_Userid(userBodyInfo.getUserInfo().getUserid());
            if (rankMale == null) {
                rankMale = new ScoreRankMale();
            }
            rankMale.setSex(userBodyInfo.getSex());
            rankMale.setAge(userBodyInfo.getAge());
            rankMale.setHeight(userBodyInfo.getHeight());
            rankMale.setWeight(userBodyInfo.getWeight());
            rankMale.setLeanmass(userBodyInfo.getLeanmass());
            rankMale.setFatmass(userBodyInfo.getFatMass());
            rankMale.setFatpercentage((float) userBodyInfo.getFatpercentage());
            rankMale.setScore(score);
            rankMale.setUserInfo(userBodyInfo.getUserInfo());
            scoreRankMaleRepository.save(rankMale);
        } else if (userBodyInfo.getSex() == 2) { // 여성
            ScoreRankFemale rankFemale = scoreRankFemaleRepository
                    .findByUserInfo_Userid(userBodyInfo.getUserInfo().getUserid());
            if (rankFemale == null) {
                rankFemale = new ScoreRankFemale();
            }
            rankFemale.setSex(userBodyInfo.getSex());
            rankFemale.setAge(userBodyInfo.getAge());
            rankFemale.setHeight(userBodyInfo.getHeight());
            rankFemale.setWeight(userBodyInfo.getWeight());
            rankFemale.setLeanmass(userBodyInfo.getLeanmass());
            rankFemale.setFatmass(userBodyInfo.getFatMass());
            rankFemale.setFatpercentage((float) userBodyInfo.getFatpercentage());
            rankFemale.setScore(score);
            rankFemale.setUserInfo(userBodyInfo.getUserInfo());
            scoreRankFemaleRepository.save(rankFemale);
        }
    }
}
