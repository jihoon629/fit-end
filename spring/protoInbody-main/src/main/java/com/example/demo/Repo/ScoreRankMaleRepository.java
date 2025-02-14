package com.example.demo.Repo;

import com.example.demo.Entity.ScoreRankMale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRankMaleRepository extends JpaRepository<ScoreRankMale, Long> {
}
