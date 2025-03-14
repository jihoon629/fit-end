package com.example.demo.Service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginAttemptService {
    private final int MAX_ATTEMPTS = 5; // 최대 로그인 시도 횟수
    private final long BLOCK_DURATION = 5 * 60; // 5분 (초 단위)
    private final ConcurrentHashMap<String, LoginAttempt> attempts = new ConcurrentHashMap<>();

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
                System.out.println("[로그] " + key + " 로그인 차단됨! 남은 시간: " + remainingMinutes + "분 (" + remainingSeconds + "초)");
                return true;
            }
        }
        return false;
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
}
