package com.example.demo.Service;

import com.example.demo.Repo.RepoScoreRankFemale;
import com.example.demo.Repo.RepoScoreRankMale;
import com.example.demo.Entity.ScoreRankMale;

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
}
