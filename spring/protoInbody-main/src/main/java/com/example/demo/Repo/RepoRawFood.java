package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.Entity.RawFood.RawFood;

public interface RepoRawFood extends JpaRepository<RawFood, Long>, JpaSpecificationExecutor<RawFood> {

    List<RawFood> findByFoodNmContaining(String foodNm);

    List<RawFood> findByFoodCdAndFoodNmContaining(String foodCd, String foodNm);

    List<RawFood> findByfoodNm(String foodNm);
}
