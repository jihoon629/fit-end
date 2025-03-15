package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.UserBodyInfo;

public interface RepoUserBodyInfo extends JpaRepository<UserBodyInfo, Long> {
    // 디비랑 연결되는거 여기에 자체적인 쿼리문 작성가능해요

    List<UserBodyInfo> findTop5ByUserInfo_UseridOrderByDateDesc(String userid);

    List<UserBodyInfo> findByAge(int age);

    @Query("SELECT u FROM UserBodyInfo u WHERE u.age = :age AND u.date = (SELECT MAX(u2.date) FROM UserBodyInfo u2 WHERE u2.userInfo.userid = u.userInfo.userid AND u2.age = :age)")
    List<UserBodyInfo> findLatestUserBodyInfoByAge(int age);
    // @Query("SELECT u FROM UserBodyInfo u WHERE u.userid = :userid ORDER BY u.date
    // DESC")
    // List<UserBodyInfo> findRecentByUserid(@Param("userid") String userid);

    @Query("SELECT u FROM UserBodyInfo u WHERE u.age = :age AND u.sex = :sex AND u.date = (SELECT MAX(u2.date) FROM UserBodyInfo u2 WHERE u2.userInfo.userid = u.userInfo.userid AND u2.age = :age AND u2.sex = :sex)")
    List<UserBodyInfo> findLatestUserBodyInfoByAgeAndSex(int age, int sex);

    @Query("SELECT u FROM UserBodyInfo u WHERE u.sex = :sex AND u.date = (SELECT MAX(u2.date) FROM UserBodyInfo u2 WHERE u2.userInfo.userid = u.userInfo.userid AND u2.sex = :sex)")
    List<UserBodyInfo> findLatestUserBodyInfoBySex(int sex);

}
