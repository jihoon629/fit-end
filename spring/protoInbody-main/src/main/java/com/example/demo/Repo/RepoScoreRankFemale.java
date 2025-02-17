package com.example.demo.Repo;

import com.example.demo.Entity.ScoreRankFemale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoScoreRankFemale extends JpaRepository<ScoreRankFemale, Long> {
    ScoreRankFemale findByUserInfo_Userid(String userid);

    List<ScoreRankFemale> findAllByOrderByScoreDesc();

}
