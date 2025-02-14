package com.example.demo.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserInfo;
import com.example.demo.Repo.RepoUserInfo;

@Service
public class UserInfoService {

    @Autowired
    private RepoUserInfo RepoUserInfo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserInfo registerUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return RepoUserInfo.save(userInfo);
    }

    public boolean authenticateUser(String userid, String password) {
        UserInfo user = RepoUserInfo.findByUserid(userid);
        return user != null && passwordEncoder.matches(password, user.getPassword()); //해시 암호화된 비밀번호 비교
    }

}
