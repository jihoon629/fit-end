package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserInfo;
import com.example.demo.Repo.RepoUserInfo;

@Service
public class UserInfoService {

    @Autowired
    private RepoUserInfo RepoUserInfo;

    public UserInfo registerUser(UserInfo userInfo) {
        return RepoUserInfo.save(userInfo);
    }

    public boolean authenticateUser(String userid, String password) {
        UserInfo user = RepoUserInfo.findByUserid(userid);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

}
