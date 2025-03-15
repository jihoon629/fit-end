package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserInfoService;

import com.example.demo.DTO.UserInfoDTO;

@RestController // 회원정보 관련 컨트롤러 입니다
@RequestMapping("/userinfo")
public class UserInfoApi {

    @Autowired
    private UserInfoService UserInfoService;

    @PostMapping("/register") // 회원가입 기능
    public UserInfoDTO registerUser(@RequestBody UserInfoDTO UserInfoDTO) {
        return UserInfoService.registerUser(UserInfoDTO);
    }

    @PostMapping("/generation")
    public String generationJwt(@RequestBody UserInfoDTO UserInfoDTO) {

        return UserInfoService.generateAPiToken(UserInfoDTO);

    }

}
