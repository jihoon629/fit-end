package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.RawFood.Nutrient;

public interface RepoNutrient extends JpaRepository<Nutrient, Long> {

}
