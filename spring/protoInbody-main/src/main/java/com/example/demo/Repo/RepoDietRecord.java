package com.example.demo.Repo;
import java.util.List;
import com.example.demo.Entity.DietRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoDietRecord extends JpaRepository<DietRecord, Long> {
    List<DietRecord> findByUserInfoUserid(String userid); //userid로 DietRecord가져옴
}
