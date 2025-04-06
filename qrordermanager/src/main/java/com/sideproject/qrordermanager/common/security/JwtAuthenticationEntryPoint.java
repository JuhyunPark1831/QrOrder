package com.sideproject.qrordermanager.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sideproject.qrordermanager.common.error.ErrorCode;
import com.sideproject.qrordermanager.common.response.ApiResponse;
import com.sideproject.qrordermanager.common.util.CookieUtil;
import com.sideproject.qrordermanager.dto.account.response.JwtResponseDto;
import com.sideproject.qrordermanager.service.account.AccountService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;
    private final AccountService accountService;
    private final CookieUtil cookieUtil;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        String refreshToken = null;

        // refresh 토큰을 쿠키에서 가져오기
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("qrOrderRefreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                }
            }
        }

        if (refreshToken == null) {
            cookieUtil.deleteCookie(response, "qrOrderAccessToken");
            cookieUtil.deleteCookie(response, "qrOrderRefreshToken");
            authFailHandler(request, response);
        } else {
            try {
                JwtResponseDto tokens = accountService.refresh(refreshToken);

                cookieUtil.setToken(response, tokens);

                // refresh 성공.
                if (request.getRequestURI().contains("api") || request.getRequestURI().contains("replace")) {
                    String jsonResponse = objectMapper.writeValueAsString(ApiResponse.error(ErrorCode.TOKEN_REGENERATE));
                    response.setCharacterEncoding("UTF-8");
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.getWriter().write(jsonResponse);
                } else {
                    response.sendRedirect(request.getRequestURI());
                }

            } catch (Exception ex) {
                cookieUtil.deleteCookie(response, "qrOrderAccessToken");
                cookieUtil.deleteCookie(response, "qrOrderRefreshToken");
                authFailHandler(request, response);
            }
        }
    }

    private void authFailHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().contains("api") || request.getRequestURI().contains("replace")) {
            String jsonResponse = objectMapper.writeValueAsString(ApiResponse.error(ErrorCode.REFRESH_TOKEN_AUTHENTICATION_FAILED));
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(jsonResponse);
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            String script = """
                <script>
                    alert('로그인이 필요합니다.');
                    window.location.href = '/account/login';
                </script>
            """;
            response.getWriter().write(script);
        }
    }
}
