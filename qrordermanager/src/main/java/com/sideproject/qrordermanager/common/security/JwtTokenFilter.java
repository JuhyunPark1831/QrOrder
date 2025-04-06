package com.sideproject.qrordermanager.common.security;

import com.sideproject.qrordermanager.common.error.ApiCustomException;
import com.sideproject.qrordermanager.common.error.ErrorCode;
import com.sideproject.qrordermanager.common.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 쿠키에서 Authorization 토큰 찾기
        Optional<String> optionalAccessToken = getTokenFromCookies(request);

        try {
            String accessToken = optionalAccessToken.orElseThrow(() ->
                    new ApiCustomException(ErrorCode.ACCESS_TOKEN_AUTHENTICATION_FAILED));

            if (jwtUtil.getTypeIdFromToken(accessToken).equals("ACCESS")) {

                // 토큰 검증
                jwtUtil.validateToken(accessToken);

                // 아이디 추출
                String acLoginId = jwtUtil.getAcLoginIdFromToken(accessToken);

                // 인증 객체 생성
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(acLoginId, null, null);

                // 인증 객체 저장
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                filterChain.doFilter(request, response);
            } else {
                SecurityContextHolder.clearContext();
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
        }
    }

    private Optional<String> getTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("qrOrderAccessToken".equals(cookie.getName())) {
                    return Optional.of(cookie.getValue());
                }
            }
        }
        return Optional.empty();
    }
}