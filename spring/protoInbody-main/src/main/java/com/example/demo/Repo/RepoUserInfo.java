package com.example.demo.Repo;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.UserInfo;

public interface RepoUserInfo extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserid(String userid);
    // 디비랑 연결되는거 여기에 자체적인 쿼리문 작성가능해요

    default int getUserSexById(String userid) {
        UserInfo userInfo = findByUserid(userid);
        return userInfo != null ? userInfo.getSex() : 0; // 성별 값이 없을 때 0 반환
    }

    default LocalDate getUserBirthById(String userid) {
        UserInfo userInfo = findByUserid(userid);
        return userInfo != null ? userInfo.getBirth() : null; // 생년월일이 없을 때 null 반환
    }
}
