package com.example.demo.Service;

import com.example.demo.Repo.RepoScoreRankFemale;
import com.example.demo.Repo.RepoScoreRankMale;
import com.example.demo.Entity.ScoreRankMale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ScoreRankFemale;

@Service
public class ScoreRankService {

    @Autowired
    private RepoScoreRankMale RepoScoreRankMale;
    @Autowired
    private RepoScoreRankFemale RepoScoreRankFemale;

    public List<ScoreRankMale> showRankMale() {
        return RepoScoreRankMale.findAllByOrderByScoreDesc();
    }

    public List<ScoreRankFemale> showRankFemale() {
        return RepoScoreRankFemale.findAllByOrderByScoreDesc();

    }
}
