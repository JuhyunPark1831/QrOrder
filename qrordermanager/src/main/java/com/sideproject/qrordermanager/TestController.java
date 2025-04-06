package com.sideproject.qrordermanager;

import com.sideproject.qrordermanager.common.util.CookieUtil;
import com.sideproject.qrordermanager.dto.account.response.JwtResponseDto;
import com.sideproject.qrordermanager.service.account.AccountService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    @Value("${spring.jwt.access-token-expire}")
    private Long ACCESS_TOKEN_EXPIRE_LENGTH;
    @Value("${spring.jwt.refresh-token-expire}")
    private Long REFRESH_TOKEN_EXPIRE_LENGTH;

    private final AccountService accountService;
    private final CookieUtil cookieUtil;

    @GetMapping
    public String test(HttpServletResponse response) {
//        JwtResponseDto tokens = accountService.login("epahs1831");
//        cookieUtil.setCookie(response, "qrOrderAccessToken", tokens.getAccessToken(), ACCESS_TOKEN_EXPIRE_LENGTH);
//        cookieUtil.setCookie(response, "qrOrderRefreshToken", tokens.getRefreshToken(), REFRESH_TOKEN_EXPIRE_LENGTH);
        return "account/login";
    }

    @GetMapping("/replace")
    public String test1() {
        return "login/login :: #test";
    }
}
