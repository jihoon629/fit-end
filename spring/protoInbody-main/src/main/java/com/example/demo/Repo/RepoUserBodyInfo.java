package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.UserBodyInfo;

public interface RepoUserBodyInfo extends JpaRepository<UserBodyInfo, Long> {
    // 디비랑 연결되는거 여기에 자체적인 쿼리문 작성가능해요

    List<UserBodyInfo> findTop5ByUserInfo_UseridOrderByDateDesc(String userid);

    List<UserBodyInfo> findByAge(int age);

    // @Query("SELECT u FROM UserBodyInfo u WHERE u.userid = :userid ORDER BY u.date
    // DESC")
    // List<UserBodyInfo> findRecentByUserid(@Param("userid") String userid);
}
