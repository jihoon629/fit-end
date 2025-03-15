package com.example.demo.Repo;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.UserInfo;

public interface RepoUserInfo extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserid(String userid);

    // 생년월일 조회 메서드 추가
    @Query("SELECT u.birth FROM UserInfo u WHERE u.userid = :userid")
    LocalDate getUserBirthById(@Param("userid") String userid);

    // 성별 조회 메서드 추가
    @Query("SELECT u.sex FROM UserInfo u WHERE u.userid = :userid")
    Integer getUserSexById(@Param("userid") String userid);

    UserInfo findSexByUserid(String userid);

}
