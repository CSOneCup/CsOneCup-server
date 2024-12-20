package com.csOneCup.csOneCup.auth;

import com.csOneCup.csOneCup.user.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if ("/api/user/signup".equals(request.getServletPath()) || "/api/user/signin".equals(request.getServletPath()) || request.getServletPath().startsWith("/swagger") || request.getServletPath().equals("/v3/api-docs/swagger-config") || request.getServletPath().equals("/api/user/healthy")) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println(request.getServletPath());

        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("Token null");
            filterChain.doFilter(request, response);
            return;
        }

        // JWT 토큰에서 사용자 정보 추출
        String userId = jwtUtil.extractUserId(authorization);
        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)) {
            System.out.println("token is expired");
            filterChain.doFilter(request, response);
            return;
        }


        // SecurityContext에 인증 객체 설정
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userId, null, null); // 권한은 필요 시 추가
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}
