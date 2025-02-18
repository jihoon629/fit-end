package com.example.demo.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.UserInfoDTO;

import com.example.demo.Entity.UserInfo;
import com.example.demo.Repo.RepoUserInfo;

@Service
public class UserInfoService {

    @Autowired
    private RepoUserInfo RepoUserInfo;

    public UserInfoDTO registerUser(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = convertToEntity(userInfoDTO);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        UserInfo savedUser = RepoUserInfo.save(userInfo);
        return convertToDTO(savedUser);
    }

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean authenticateUser(UserInfoDTO UserInfoDTO) {
        UserInfo user = RepoUserInfo.findByUserid(UserInfoDTO.getUserid());
        return user != null && passwordEncoder.matches(UserInfoDTO.getPassword(), user.getPassword()); // 해시 암호화된 비밀번호
                                                                                                       // 비교
    }

    // DTO를 엔티티로 변환
    private UserInfo convertToEntity(UserInfoDTO userInfoDTO) {
        return new UserInfo(
                userInfoDTO.getId(),
                userInfoDTO.getUserid(),
                userInfoDTO.getPassword(),
                userInfoDTO.getEmail(),
                userInfoDTO.getSex(),
                userInfoDTO.getRegion1(),
                userInfoDTO.getRegion2(),
                userInfoDTO.getBirth());
    }

    private UserInfoDTO convertToDTO(UserInfo userInfo) {
        return new UserInfoDTO(
                userInfo.getId(),
                userInfo.getUserid(),
                userInfo.getPassword(),
                userInfo.getEmail(),
                userInfo.getSex(),
                userInfo.getRegion1(),
                userInfo.getRegion2(),
                userInfo.getBirth());
    }
}
