package com.example.demo.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.FoodDto;
import com.example.demo.DTO.UserInfoDTO;
import com.example.demo.Entity.DietRecord;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Jwt.AuthenticationResponse;
import com.example.demo.Jwt.JwtUtil;
import com.example.demo.Repo.RepoDietRecord;
import com.example.demo.Repo.RepoUserInfo;
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

    @Autowired
    RepoDietRecord dietRecordRepository;// 식단 기록 저장소

    @Autowired
    RepoUserInfo userInfoRepository; // 유저 정보 저장소

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

        UserInfo userInfo = userInfoRepository.findByUserid(foodDto.getUserid());
        if (userInfo == null) {
            return ResponseEntity.status(404).body(Map.of("error", "사용자 찾을 수 없음"));
        }

        DietRecord dietRecord = new DietRecord();
        dietRecord.setUserInfo(userInfo);
        dietRecord.setTimestamp(foodDto.getTimestamp());
        dietRecord.setDietMemo(foodDto.getDietMemo());
        dietRecord.setTotalcalori(foodDto.getEnerc());
        dietRecord.setTotalcarbs(foodDto.getChocdf());
        dietRecord.setTotalprotein(foodDto.getProt());
        dietRecord.setTotalfat(foodDto.getFatce());
        dietRecord.setFoodName(foodDto.getFoodNm());

        dietRecordRepository.save(dietRecord);
        System.out.println("음식 기록이 성공적으로 저장되었습니다!");

        return ResponseEntity.ok(Map.of("message", "음식 기록이 성공적으로 저장되었습니다!"));
    }
}
