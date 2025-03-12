package com.example.demo.Jwt;

import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        String username = null;
        String jwt = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("쿠키 이름: " + cookie.getName() + ", 값: " + cookie.getValue());
                if ("jwt".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    username = jwtUtil.extractUsername(jwt);
                    System.out.println("추출된 username: " + username);
                    break;
                }
            }
        }

        if (jwt != null && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("검증할 유저명: " + username);
            if (jwtUtil.validateToken(jwt, username)) {
                System.out.println("JWT 유효성 검사 통과!");
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        username, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                System.out.println("JWT 유효성 검사 실패!");
            }
        }

        chain.doFilter(request, response);
    }
}
