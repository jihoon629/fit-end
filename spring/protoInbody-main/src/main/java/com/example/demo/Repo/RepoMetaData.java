package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.RawFood.MetaData;

public interface RepoMetaData extends JpaRepository<MetaData, Long> {

}
