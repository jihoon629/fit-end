package com.example.demo.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;

import java.time.Duration;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import java.util.Date;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "my_super_secret_key_which_is_at_least_32_chars_long"; // 반드시 32자 이상으로 설정
    private final Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username, int hour) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * hour)) // 3시간 유효
                .signWith(secretKey, SignatureAlgorithm.HS256) // 올바른 서명 방식 적용
                .compact();
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        System.out.println("JWT에서 추출된 유저명: " + extractedUsername);
        System.out.println("비교할 유저명: " + username);

        return (extractedUsername != null && extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // setSigningKey() 수정
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public ResponseCookie createJwtCookie(String jwt, int hour) {
        return ResponseCookie.from("jwt", jwt)
                .httpOnly(true) // JavaScript에서 접근 불가
                .secure(false) // HTTPS 환경에서만 전송 (개발 중에는 false)
                .sameSite("Lax") // HTTP에서는 Lax가 기본값
                .path("/") // 모든 경로에서 쿠키 사용 가능
                .maxAge(Duration.ofHours(hour)) // 10시간 유지
                .build();
    }

    public String validateToken(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        String jwt = null;
        String userid = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    userid = extractUsername(jwt);
                    break;
                }
            }
        }

        if (userid != null && validateToken(jwt, userid)) {
            return userid; // 유효하면 userid 반환
        }
        return null; // 유효하지 않으면 null 반환
    }
}