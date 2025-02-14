package com.example.demo.Repo;

import com.example.demo.Entity.ScoreRankFemale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRankFemaleRepository extends JpaRepository<ScoreRankFemale, Long> {
}
