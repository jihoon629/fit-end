package com.example.demo.Service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.DTO.UserInfoDTO;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Repo.RepoUserInfo;

@Service
public class LoginAttemptService {
    private final int MAX_ATTEMPTS = 5; // 최대 로그인 시도 횟수
    private final long BLOCK_DURATION = 5 * 60; // 5분 (초 단위)
    private final ConcurrentHashMap<String, LoginAttempt> attempts = new ConcurrentHashMap<>();
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private RepoUserInfo RepoUserInfo;

    // 로그인 관련 서비스
    public boolean authenticateUser(UserInfoDTO UserInfoDTO) {
        UserInfo user = RepoUserInfo.findByUserid(UserInfoDTO.getUserid());
        return user != null && passwordEncoder.matches(UserInfoDTO.getPassword(), user.getPassword()); // 해시 암호화된 비밀번호
                                                                                                       // 비교
    }

    static class LoginAttempt {
        int count; // 실패 횟수
        LocalDateTime blockedUntil; // 차단 해제 시간

        LoginAttempt() {
            this.count = 0;
            this.blockedUntil = null;
        }
    }

    // 로그인 차단 여부 확인
    public boolean isBlocked(String key) {
        LoginAttempt attempt = attempts.get(key);
        if (attempt != null && attempt.blockedUntil != null) {
            if (LocalDateTime.now().isBefore(attempt.blockedUntil)) {
                long remainingSeconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), attempt.blockedUntil);
                long remainingMinutes = remainingSeconds / 60;
                System.out.println(
                        "[로그] " + key + " 로그인 차단됨! 남은 시간: " + remainingMinutes + "분 (" + remainingSeconds + "초)");
                return true;
            }
        }
        return false;
    }

    public Map<String, Object> checkAndHandleBlock(String key) {
        if (isBlocked(key)) {
            LocalDateTime blockedUntil = getBlockedUntil(key);
            long remainingSeconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), blockedUntil);
            long remainingMinutes = remainingSeconds / 60;

            System.out.println("[로그] " + key + " 로그인 차단됨! " + remainingMinutes + "분 후 로그인 가능");

            return Map.of(
                    "isBlocked", true,
                    "remainingMinutes", remainingMinutes,
                    "remainingSeconds", remainingSeconds);
        }
        return Map.of("isBlocked", false);
    }

    // 로그인 실패 시 시도 횟수 증가
    public void loginFailed(String key) {
        LoginAttempt attempt = attempts.computeIfAbsent(key, k -> new LoginAttempt());
        attempt.count++;

        System.out.println("[로그] " + key + " 로그인 실패 횟수: " + attempt.count);

        if (attempt.count >= MAX_ATTEMPTS) {
            attempt.blockedUntil = LocalDateTime.now().plusSeconds(BLOCK_DURATION);
            System.out.println("[로그] " + key + " 로그인 차단됨! 차단 해제 시간: " + attempt.blockedUntil);
        }
    }

    // 로그인 성공 시 시도 횟수 초기화
    public void resetAttempts(String key) {
        attempts.remove(key);
        System.out.println("[로그] " + key + " 로그인 성공! 시도 횟수 초기화됨.");
    }

    // 차단 해제 시간 반환
    public LocalDateTime getBlockedUntil(String key) {
        LoginAttempt attempt = attempts.get(key);
        return (attempt != null) ? attempt.blockedUntil : null;
    }

    // 클라이언트 IP 가져오기 (리버스 프록시 고려)
    public String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        return (xfHeader == null) ? request.getRemoteAddr() : xfHeader.split(",")[0];
    }
}
