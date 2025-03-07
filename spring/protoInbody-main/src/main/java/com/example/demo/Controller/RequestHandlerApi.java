package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.FoodDto;
import com.example.demo.DTO.UserInfoDTO;
import com.example.demo.Service.FoodService;
import com.example.demo.Service.UserInfoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController // 클라이언트에서 서버에서 특정한 행동을 요청 받고 처리하는 컨트롤러 입니다
@RequestMapping("/request")
public class RequestHandlerApi {

    @Autowired
    UserInfoService UserInfoService;

    @Autowired
    FoodService FoodService;

    @PostMapping("/login") // 로그인 관련 컨트롤러
    public ResponseEntity<String> loginUser(@RequestBody UserInfoDTO UserInfoDTO) {
        boolean isAuthenticated = UserInfoService.authenticateUser(UserInfoDTO);
        if (isAuthenticated) {
            System.out.println("로그인성공");
            return ResponseEntity.ok("Login successful");
        } else {
            System.out.println("로그인실패");
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/foodname/{foodNm}")
    public ResponseEntity<List<FoodDto>> FoodName(@PathVariable String foodNm) {
        System.out.println(foodNm);
        List<FoodDto> foodDetails = FoodService.getFoodDetails(foodNm);
        return ResponseEntity.ok(foodDetails);
    }

}
