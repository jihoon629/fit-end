package com.example.demo.Controller;

import java.util.Map;

import com.example.demo.Service.LoginAttemptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.UserInfoDTO;
import com.example.demo.Jwt.JwtUtil;

@RestController // 클라이언트에서 서버에서 특정한 행동을 요청 받고 처리하는 컨트롤러 입니다
@RequestMapping("/login")
public class LoginSecurity {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    LoginAttemptService loginAttemptService;

    @PostMapping("/login") // 로그인 관련 컨트롤러
    public ResponseEntity<?> loginUser(@RequestBody UserInfoDTO userInfoDTO, HttpServletRequest request) {
        String clientIp = loginAttemptService.getClientIP(request);
        String loginKey = userInfoDTO.getUserid() + "|" + clientIp; // 사용자 ID + IP 기준

        // 로그인 차단 여부 확인
        Map<String, Object> blockInfo = loginAttemptService.checkAndHandleBlock(loginKey);
        if ((boolean) blockInfo.get("isBlocked")) {
            long remainingMinutes = (long) blockInfo.get("remainingMinutes");

            return ResponseEntity.status(403).body(Map.of(
                    "error", "로그인 시도 횟수 초과! " + remainingMinutes + "분 후 다시 시도하세요."));
        }

        // 아이디 비번 검증
        if (loginAttemptService.authenticateUser(userInfoDTO)) {
            System.out.println("[로그] 로그인 성공: " + userInfoDTO.getUserid());

            // 로그인 성공 → 시도 횟수 초기화
            loginAttemptService.resetAttempts(loginKey);

            // 토큰 생성
            String jwt = jwtUtil.generateToken(userInfoDTO.getUserid(), 3);
            ResponseCookie jwtCookie = jwtUtil.createJwtCookie(jwt, 10);

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(Map.of("message", "Login successful"));
        } else {
            System.out.println("[로그] 로그인 실패: " + userInfoDTO.getUserid());

            // 로그인 실패 → 시도 횟수 증가
            loginAttemptService.loginFailed(loginKey);

            return ResponseEntity.status(401).body(Map.of(
                    "error", "Invalid credentials"));
        }
    }

    @PostMapping("/logout")
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
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        // 검사
        String userid = jwtUtil.validateToken(request);

        if (userid != null) {
            return ResponseEntity.ok(Map.of("userid", userid)); // 유효하면 jwt 반환
        } else {
            return ResponseEntity.status(401).body("Unauthorized"); // 유효하지 않으면 401 응답
        }
    }

}
