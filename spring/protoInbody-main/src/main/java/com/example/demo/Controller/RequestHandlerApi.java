package com.example.demo.Controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import com.example.demo.Service.LoginAttemptService;
import io.swagger.v3.oas.annotations.Parameter;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // 클라이언트에서 서버에서 특정한 행동을 요청 받고 처리하는 컨트롤러 입니다
@RequestMapping("/request")
@Tag(name = "요청 핸들러 API", description = "사용자의 로그인, 음식 검색 및 데이터 기록을 처리하는 API")
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

    @Autowired
    LoginAttemptService loginAttemptService;

    // 클라이언트 IP 가져오기 (리버스 프록시 고려)
    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        return (xfHeader == null) ? request.getRemoteAddr() : xfHeader.split(",")[0];
    }

    @GetMapping("/up")
    @Operation(summary = "CSV 데이터 저장", description = "CSV 파일에서 데이터를 저장합니다. 주의: 중복 실행 금지")
    public String saveCsv() {
        SaveRawFood.saveFromCsv();
        return "절대 2번 누르지 마시오";
    }

    @PostMapping("/login") // 로그인 관련 컨트롤러
    @Operation(summary = "사용자 로그인", description = "사용자의 로그인 정보를 확인하고 JWT 토큰을 반환합니다.")
    public ResponseEntity<?> loginUser(@RequestBody UserInfoDTO userInfoDTO, HttpServletRequest request) {
        String clientIp = getClientIP(request);
        String loginKey = userInfoDTO.getUserid() + "|" + clientIp; // 사용자 ID + IP 기준

        // 로그인 차단 여부 확인
        if (loginAttemptService.isBlocked(loginKey)) {
            LocalDateTime blockedUntil = loginAttemptService.getBlockedUntil(loginKey);
            long remainingSeconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), blockedUntil);
            long remainingMinutes = remainingSeconds / 60;

            System.out.println("[로그] " + loginKey + " 로그인 차단됨! " + remainingMinutes + "분 후 로그인 가능");

            return ResponseEntity.status(403).body(Map.of(
                    "error", "로그인 시도 횟수 초과! " + remainingMinutes + "분 후 다시 시도하세요."
            ));
        }

        boolean isAuthenticated = UserInfoService.authenticateUser(userInfoDTO);
        if (isAuthenticated) {
            System.out.println("[로그] 로그인 성공: " + userInfoDTO.getUserid());

            // 로그인 성공 → 시도 횟수 초기화
            loginAttemptService.resetAttempts(loginKey);

            String jwt = jwtUtil.generateToken(userInfoDTO.getUserid());
            ResponseCookie jwtCookie = ResponseCookie.from("jwt", jwt)
                    .httpOnly(true) // JavaScript에서 접근 불가
                    .secure(false) // HTTPS 환경에서만 전송 (개발 중에는 false)
                    .sameSite("Lax") // HTTP에서는 Lax가 기본값
                    .path("/") // 모든 경로에서 쿠키 사용 가능
                    .maxAge(Duration.ofHours(10)) // 10시간 유지
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(Map.of("message", "Login successful"));
        } else {
            System.out.println("[로그] 로그인 실패: " + userInfoDTO.getUserid());

            // 로그인 실패 → 시도 횟수 증가
            loginAttemptService.loginFailed(loginKey);

            return ResponseEntity.status(401).body(Map.of(
                    "error", "Invalid credentials"
            ));
        }
    }

    @PostMapping("/logout")
    @Operation(summary = "사용자 로그아웃", description = "JWT 토큰을 삭제하여 사용자를 로그아웃합니다.")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(false) // HTTPS 환경에서만 전송 (개발 중에는 false)
                .sameSite("Lax") // HTTP에서는 Lax가 기본값
                .path("/")
                .maxAge(0) // 즉시 만료
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(Map.of("message", "Logged out successfully"));
    }

    @GetMapping("/validate")
    @Operation(summary = "JWT 유효성 검사", description = "JWT 쿠키를 확인하여 사용자 ID를 반환합니다.")
    public ResponseEntity<?> validateToken(
            @Parameter(description = "JWT 인증 쿠키", example = "your-jwt-token")
            HttpServletRequest request) {

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
    @Operation(summary = "음식 이름 검색", description = "입력된 음식명을 기반으로 해당 음식 정보를 반환합니다.")
    public ResponseEntity<List<FoodDto>> FoodName(
            @Parameter(description = "검색할 음식 이름", example = "옥수수")
            @PathVariable String foodNm) {
        System.out.println(foodNm);
        List<FoodDto> foodDetails = FoodService.getFoodDetails(foodNm);
        return ResponseEntity.ok(foodDetails);
    }

    @PostMapping("/saveFoodRecord")
    @Operation(summary = "음식 기록 저장", description = "사용자가 섭취한 음식 정보를 저장합니다.")
    public ResponseEntity<Map<String, String>> saveFoodRecord(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "기록할 음식 정보")
            @RequestBody FoodDto foodDto) {
        System.out.println("전송받은 음식 데이터: " + foodDto);

        if (foodDto == null || foodDto.getUserid() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "잘못된 요청: 사용자 ID 누락"));
        }

        FoodService.saveFood(foodDto);
        return ResponseEntity.ok(Map.of("message", "음식 기록이 성공적으로 저장되었습니다!"));
    }

    // 사용자의 diet_record 조회
    @GetMapping("/diet-records/{userid}")
    @Operation(summary = "사용자 식단 기록 조회", description = "사용자의 식단 기록을 반환합니다.")
    public ResponseEntity<List<DietRecord>> getUserDietRecords(
            @Parameter(description = "사용자 ID", example = "user123")
            @PathVariable String userid) {

        // 사용자 ID로 식단 기록 가져오기
        List<DietRecord> dietRecords = FoodService.getDietRecordsByUser(userid);
        return ResponseEntity.ok(dietRecords);

    }
}
