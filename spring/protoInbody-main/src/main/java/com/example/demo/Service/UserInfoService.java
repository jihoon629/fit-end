package com.example.demo.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.UserInfoDTO;

import com.example.demo.Entity.UserInfo;
import com.example.demo.Jwt.JwtUtil;
import com.example.demo.Repo.RepoUserInfo;
import com.example.demo.Service.Utile.ConversionService;

@Service
public class UserInfoService {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private RepoUserInfo RepoUserInfo;

    @Autowired
    private ConversionService EntityConversionService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입 서비스
    public UserInfoDTO registerUser(UserInfoDTO userInfoDTO) {
        userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
        RepoUserInfo.save(EntityConversionService.convertToEntity(userInfoDTO, UserInfo.class));
        // generateAPiToken(userInfoDTO);
        return userInfoDTO;
    }

    public String generateAPiToken(UserInfoDTO UserInfoDTO) {
        UserInfo userInfo = RepoUserInfo.findByUserid(UserInfoDTO.getUserid()); // userInfo 객체 가져오기

        if (userInfo == null) {
            return "오류"; // userInfo가 null일 경우 "오류"를 반환합니다.
        }

        // 기존 JWT가 있으면 반환합니다.
        if (userInfo.getJwt() != null && !userInfo.getJwt().isEmpty()) {
            return userInfo.getJwt();
        }

        // 새로운 JWT를 생성하고 저장합니다.
        String jwt = jwtUtil.generateToken(UserInfoDTO.getUserid(), 24);
        userInfo.setJwt(jwt);
        RepoUserInfo.save(userInfo);

        return userInfo.getJwt();
    }

}
