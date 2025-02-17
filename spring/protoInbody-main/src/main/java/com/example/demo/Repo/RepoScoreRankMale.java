package com.example.demo.Repo;

import com.example.demo.Entity.ScoreRankMale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoScoreRankMale extends JpaRepository<ScoreRankMale, Long> {

    ScoreRankMale findByUserId(String userId);

    List<ScoreRankMale> findAllByOrderByScoreDesc();

}
