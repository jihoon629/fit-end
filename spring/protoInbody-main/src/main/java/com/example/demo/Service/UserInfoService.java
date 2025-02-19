package com.example.demo.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.UserInfoDTO;
import org.modelmapper.ModelMapper;

import com.example.demo.Entity.UserInfo;
import com.example.demo.Repo.RepoUserInfo;

@Service
public class UserInfoService {

    @Autowired
    private RepoUserInfo RepoUserInfo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private ModelMapper modelMapper = new ModelMapper();

    // 회원가입 서비스
    public UserInfoDTO registerUser(UserInfoDTO userInfoDTO) {
        userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
        RepoUserInfo.save(convertToEntity(userInfoDTO));
        return userInfoDTO;
    }

    // 로그인 관련 서비스
    public boolean authenticateUser(UserInfoDTO UserInfoDTO) {
        UserInfo user = RepoUserInfo.findByUserid(UserInfoDTO.getUserid());
        return user != null && passwordEncoder.matches(UserInfoDTO.getPassword(), user.getPassword()); // 해시 암호화된 비밀번호
                                                                                                       // 비교
    }

    // DTO를 엔티티로 변환
    private UserInfo convertToEntity(UserInfoDTO userInfoDTO) {
        return modelMapper.map(userInfoDTO, UserInfo.class);
    }

}
