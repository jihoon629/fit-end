package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserInfoService;
import com.example.demo.Service.UserBodyInfoService;
import com.example.demo.DTO.FoodDto;
import com.example.demo.DTO.UserBodyInfoDTO;
import com.example.demo.DTO.UserInfoDTO;

@RestController // 업로드 관련 컨트롤러 입니다
@RequestMapping("/userinfo")
public class UserInfoApi {

    @Autowired
    private UserInfoService UserInfoService;

    @Autowired
    private UserBodyInfoService UserBodyInfoService;

    @PostMapping("/register") // 회원가입 기능
    public UserInfoDTO registerUser(@RequestBody UserInfoDTO UserInfoDTO) {
        return UserInfoService.registerUser(UserInfoDTO);
    }

    @PostMapping("/recorduserbody") // 신체정보 기록 컨트롤러
    public ResponseEntity<UserBodyInfoDTO> recordUserBody(@RequestBody UserBodyInfoDTO UserBodyInfoDTO) {
        System.out.println("[LOG] 데이터 수신: " + UserBodyInfoDTO);
        UserBodyInfoDTO savedRecord = UserBodyInfoService.recordeUserBodyInfo(UserBodyInfoDTO);
        return ResponseEntity.ok(savedRecord);
    }

    @PostMapping("/recordfood") // 신체정보 기록 컨트롤러
    public ResponseEntity<FoodDto> recordfood(@RequestBody FoodDto FoodDto) {
        System.out.println("[LOG] 데이터 수신: " + FoodDto);
        return ResponseEntity.ok(FoodDto);
    }

}
