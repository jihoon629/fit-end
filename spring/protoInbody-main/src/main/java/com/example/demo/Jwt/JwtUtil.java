package com.example.demo.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "my_super_secret_key_which_is_at_least_32_chars_long"; // 반드시 32자 이상으로 설정
    private final Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3)) // 3시간 유효
                .signWith(secretKey, SignatureAlgorithm.HS256) // 올바른 서명 방식 적용
=======
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10시간
                .signWith(secretKey)
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
=======
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
