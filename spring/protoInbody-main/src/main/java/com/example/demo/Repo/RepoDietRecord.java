package com.example.demo.Repo;

import com.example.demo.Entity.DietRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoDietRecord extends JpaRepository<DietRecord, Long> {
}
