package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.UserInfo;
import com.example.demo.Service.UserInfoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController // 클라이언트에서 서버에서 특정한 행동을 요청 받고 처리하는 컨트롤러 입니다
@RequestMapping("/request")
public class RequestHandlerApi {

    @Autowired
    UserInfoService UserInfoService;

    @PostMapping("/login") // 로그인 관련 컨트롤러
    public ResponseEntity<String> loginUser(@RequestBody UserInfo userInfo) {
        boolean isAuthenticated = UserInfoService.authenticateUser(userInfo.getUserid(), userInfo.getPassword());
        if (isAuthenticated) {
            System.out.println("로그인성공");
            return ResponseEntity.ok("Login successful");
        } else {
            System.out.println("로그인실패");

            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}
