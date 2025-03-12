package com.example.demo.Controller;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.FoodDto;
import com.example.demo.DTO.UserInfoDTO;
import com.example.demo.Entity.DietRecord;
import com.example.demo.Jwt.JwtUtil;

import com.example.demo.Service.FoodService;
import com.example.demo.Service.UserBodyInfoService;
import com.example.demo.Service.UserInfoService;
import com.example.demo.Service.Convert.SaveRawFood;

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

    @Autowired
    SaveRawFood SaveRawFood;

    @GetMapping("/up")
    public String savecvs() {

        SaveRawFood.saveFromCsv();

        return "기다려";
    }

    @PostMapping("/login") // 로그인 관련 컨트롤러
    public ResponseEntity<?> loginUser(@RequestBody UserInfoDTO UserInfoDTO) {
        boolean isAuthenticated = UserInfoService.authenticateUser(UserInfoDTO);
        if (isAuthenticated) {
            System.out.println("로그인성공");
            String jwt = jwtUtil.generateToken(UserInfoDTO.getUserid());
            ResponseCookie jwtCookie = ResponseCookie.from("jwt", jwt)
                    .httpOnly(true) // JavaScript에서 접근 불가
                    .secure(false) // HTTPS 환경에서만 전송 (개발 중에는 false)
                    .path("/") // 모든 경로에서 쿠키 사용 가능
                    .maxAge(Duration.ofHours(10)) // 10시간 유지
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(Map.of("message", "Login successful"));
        } else {
            System.out.println("로그인실패");
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0) // 즉시 만료
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(Map.of("message", "Logged out successfully"));
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {

        System.out.println("유효성");
        Cookie[] cookies = request.getCookies();
        String jwt = null;
        String userid = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    userid = jwtUtil.extractUsername(jwt);
                    break;
                }
            }
        }

        if (userid != null && jwtUtil.validateToken(jwt, userid)) {
            return ResponseEntity.ok(Map.of("userid", userid)); // 유효하면 userid 반환
        } else {
            return ResponseEntity.status(401).body("Unauthorized"); // 유효하지 않으면 401 응답
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