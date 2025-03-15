package com.example.demo.Service;

import com.example.demo.Repo.RepoScoreRankFemale;
import com.example.demo.Repo.RepoScoreRankMale;
import com.example.demo.Repo.RepoUserInfo;
import com.example.demo.Service.Utile.EntityConversionService;
import com.example.demo.Entity.ScoreRankMale;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ScoreRankFemaleDTO;
import com.example.demo.DTO.ScoreRankMaleDTO;
import com.example.demo.DTO.UserBodyInfoDTO;
import com.example.demo.Entity.ScoreRankFemale;

@Service
public class ScoreRankService {

    @Autowired
    private RepoScoreRankMale RepoScoreRankMale;

    @Autowired
    private RepoScoreRankFemale RepoScoreRankFemale;

    @Autowired
    private RepoUserInfo RepoUserInfo;

    @Autowired
    EntityConversionService EntityConversionService;

    // 남성쪽 점수랭킹
    public List<ScoreRankMaleDTO> showRankMale() {
        List<ScoreRankMale> scoreRankMales = RepoScoreRankMale.findAllByOrderByScoreDesc();
        return scoreRankMales.stream()
                .map(scoreRankMale -> new ScoreRankMaleDTO(scoreRankMale))
                .collect(Collectors.toList());
    }

    // 여성쪽 점수랭킹
    public List<ScoreRankFemaleDTO> showRankFemale() {
        List<ScoreRankFemale> scoreRankFemales = RepoScoreRankFemale.findAllByOrderByScoreDesc();
        return scoreRankFemales.stream()
                .map(scoreRankFemale -> new ScoreRankFemaleDTO(scoreRankFemale))
                .collect(Collectors.toList());
    }

    // 사용자 정보를 기반으로 점수를 저장하는 메서드입니다.
    // 성별(sex)에 따라 score_rank_male 또는 score_rank_female 테이블에 저장합니다.
    public void saveToScoreRank(UserBodyInfoDTO UserBodyInfoDTO) {
        String userid = UserBodyInfoDTO.getUserid();
        ScoreRankMale ScoreRankMale = RepoScoreRankMale.findByUserInfo_Userid(userid);
        ScoreRankFemale ScoreRankFemale = RepoScoreRankFemale.findByUserInfo_Userid(userid);

        if (UserBodyInfoDTO.getSex() == 1) { // 남성
            if (ScoreRankMale == null) {
                ScoreRankMale = new ScoreRankMale();
                ScoreRankMale.setUserInfo(RepoUserInfo.findByUserid(userid));

            }
            ScoreRankMaleDTO ScoreRankMaleDTO = new ScoreRankMaleDTO(ScoreRankMale);
            ScoreRankMaleDTO.setSex(UserBodyInfoDTO.getSex());
            ScoreRankMaleDTO.setAge(UserBodyInfoDTO.getAge());
            ScoreRankMaleDTO.setHeight(UserBodyInfoDTO.getHeight());
            ScoreRankMaleDTO.setWeight(UserBodyInfoDTO.getWeight());
            ScoreRankMaleDTO.setLeanmass(UserBodyInfoDTO.getLeanmass());
            ScoreRankMaleDTO.setFatmass(UserBodyInfoDTO.getFatmass());
            ScoreRankMaleDTO.setFatpercentage((float) UserBodyInfoDTO.getFatpercentage());
            ScoreRankMaleDTO.setScore((int) UserBodyInfoDTO.getInbodyScore());
            ScoreRankMaleDTO.setUserid(UserBodyInfoDTO.getUserid());

            RepoScoreRankMale.save(EntityConversionService.convertToEntity(ScoreRankMaleDTO, ScoreRankMale.class));

        } else if (UserBodyInfoDTO.getSex() == 2) { // 여성
            if (ScoreRankFemale == null) {
                ScoreRankFemale = new ScoreRankFemale();
                ScoreRankFemale.setUserInfo(RepoUserInfo.findByUserid(userid));

            }
            ScoreRankFemaleDTO ScoreRankFemaleDTO = new ScoreRankFemaleDTO(ScoreRankFemale);
            ScoreRankFemaleDTO.setSex(UserBodyInfoDTO.getSex());
            ScoreRankFemaleDTO.setAge(UserBodyInfoDTO.getAge());
            ScoreRankFemaleDTO.setHeight(UserBodyInfoDTO.getHeight());
            ScoreRankFemaleDTO.setWeight(UserBodyInfoDTO.getWeight());
            ScoreRankFemaleDTO.setLeanmass(UserBodyInfoDTO.getLeanmass());
            ScoreRankFemaleDTO.setFatmass(UserBodyInfoDTO.getFatmass());
            ScoreRankFemaleDTO.setFatpercentage((float) UserBodyInfoDTO.getFatpercentage());
            ScoreRankFemaleDTO.setScore((int) UserBodyInfoDTO.getInbodyScore());
            ScoreRankFemaleDTO.setUserid(UserBodyInfoDTO.getUserid());

            RepoScoreRankFemale
                    .save(EntityConversionService.convertToEntity(ScoreRankFemaleDTO, ScoreRankFemale.class));
        }

    }

}