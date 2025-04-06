package com.sideproject.qrordermanager.common.util;

import com.sideproject.qrordermanager.dto.account.response.JwtResponseDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieUtil {

    @Value("${spring.jwt.access-token-expire}")
    private Long ACCESS_TOKEN_EXPIRE_LENGTH;
    @Value("${spring.jwt.refresh-token-expire}")
    private Long REFRESH_TOKEN_EXPIRE_LENGTH;

    public void setToken(HttpServletResponse response, JwtResponseDto tokens) {
        setCookie(response, "qrOrderAccessToken", tokens.getAccessToken(), ACCESS_TOKEN_EXPIRE_LENGTH);
        setCookie(response, "qrOrderRefreshToken", tokens.getRefreshToken(), REFRESH_TOKEN_EXPIRE_LENGTH);
    }

    public void setCookie(HttpServletResponse response, String key, String value, Long expire) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge((int) (expire / 1000));
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public void deleteCookie(HttpServletResponse response, String key) {
        Cookie cookie = new Cookie(key, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
