package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.RawFood.RawFood;

public interface RepoRawFood extends JpaRepository<RawFood, Long> {

}
