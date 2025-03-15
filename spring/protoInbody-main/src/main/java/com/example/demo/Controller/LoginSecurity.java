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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // 로그인 및 보안 관련 컨트롤러
@RequestMapping("/login")
@Tag(name = "Authentication", description = "로그인 및 보안 관련 API")
public class LoginSecurity {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Operation(
            summary = "로그인",
            description = "사용자 아이디와 비밀번호를 검증하여 로그인 처리합니다. 로그인 시도 횟수를 관리하며, 성공 시 JWT 토큰을 발급합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login successful",
                            content = @Content(schema = @Schema(implementation = Map.class))),
                    @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content),
                    @ApiResponse(responseCode = "403", description = "로그인 시도 횟수 초과", content = @Content)
            }
    )
    @PostMapping("/login") // 로그인 관련 컨트롤러
    public ResponseEntity<?> loginUser(
            @RequestBody UserInfoDTO userInfoDTO, HttpServletRequest request) {
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

    @Operation(
            summary = "로그아웃",
            description = "사용자의 JWT 토큰을 만료시켜 로그아웃 처리합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Logged out successfully",
                            content = @Content(schema = @Schema(implementation = Map.class)))
            }
    )
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

    @Operation(
            summary = "토큰 검증",
            description = "요청에 포함된 JWT 토큰을 검증하여 사용자의 아이디를 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "유효한 토큰",
                            content = @Content(schema = @Schema(implementation = Map.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            }
    )
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
