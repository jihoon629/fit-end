package com.example.demo.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.UserInfoDTO;

import com.example.demo.Entity.UserInfo;
import com.example.demo.Jwt.JwtUtil;
import com.example.demo.Repo.RepoUserInfo;
import com.example.demo.Service.Utile.EntityConversionService;

@Service
public class UserInfoService {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private RepoUserInfo RepoUserInfo;

    @Autowired
    private EntityConversionService EntityConversionService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입 서비스
    public UserInfoDTO registerUser(UserInfoDTO userInfoDTO) {
        userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
        RepoUserInfo.save(EntityConversionService.convertToEntity(userInfoDTO, UserInfo.class));
        // generateAPiToken(userInfoDTO);
        return userInfoDTO;
    }

    public void generateAPiToken(UserInfoDTO UserInfoDTO) {
        UserInfo userInfo = RepoUserInfo.findByUserid(UserInfoDTO.getUserid()); // userInfo 객체 가져오기

        if (userInfo != null) {
            String jwt = jwtUtil.generateToken(UserInfoDTO.getUserid(), 24);
            userInfo.setJwt(jwt);
            RepoUserInfo.save(userInfo);
        }
    }

}
