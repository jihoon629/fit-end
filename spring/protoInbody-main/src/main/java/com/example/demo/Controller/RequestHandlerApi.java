package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.FoodDto;
import com.example.demo.DTO.UserInfoDTO;
import com.example.demo.Entity.DietRecord;
import com.example.demo.Jwt.AuthenticationResponse;
import com.example.demo.Jwt.JwtUtil;

import com.example.demo.Service.FoodService;
import com.example.demo.Service.UserBodyInfoService;
import com.example.demo.Service.UserInfoService;

@RestController // 클라이언트에서 서버에서 특정한 행동을 요청 받고 처리하는 컨트롤러 입니다
@RequestMapping("/request")
public class RequestHandlerApi {

    @Autowired
    UserInfoService UserInfoService;
    @Autowired
    UserBodyInfoService UserBodyInfoService;

    @Autowired
    FoodService FoodService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login") // 로그인 관련 컨트롤러
    public ResponseEntity<?> loginUser(@RequestBody UserInfoDTO UserInfoDTO) {
        boolean isAuthenticated = UserInfoService.authenticateUser(UserInfoDTO);
        if (isAuthenticated) {
            System.out.println("로그인성공");
            String jwt = jwtUtil.generateToken(UserInfoDTO.getUserid());
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } else {
            System.out.println("로그인실패");
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/foodname/{foodNm}") // 음식 이름으로 검색하는 컨트롤러
    public ResponseEntity<List<FoodDto>> FoodName(@PathVariable String foodNm) {
        System.out.println(foodNm);
        List<FoodDto> foodDetails = FoodService.getFoodDetails(foodNm);
        return ResponseEntity.ok(foodDetails);
    }

    @PostMapping("/saveFoodRecord")
    public ResponseEntity<Map<String, String>> saveFoodRecord(@RequestBody FoodDto foodDto) {
        System.out.println("전송받은 음식 데이터: " + foodDto);

        if (foodDto == null || foodDto.getUserid() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "잘못된 요청: 사용자 ID 누락"));
        }

        FoodService.saveFood(foodDto);
        return ResponseEntity.ok(Map.of("message", "음식 기록이 성공적으로 저장되었습니다!"));
    }

    // 사용자의 diet_record 조회
    @GetMapping("/diet-records/{userid}")
    public ResponseEntity<List<DietRecord>> getUserDietRecords(@PathVariable String userid) {

        // 사용자 ID로 식단 기록 가져오기
        List<DietRecord> dietRecords = FoodService.getDietRecordsByUser(userid);
        return ResponseEntity.ok(dietRecords);

    }

}