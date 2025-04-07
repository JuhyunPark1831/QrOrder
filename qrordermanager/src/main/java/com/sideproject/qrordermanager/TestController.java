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

    @GetMapping
    public String test(HttpServletResponse response) {
        return "menuOption/createMenuOption";
    }

    //todo: leftMenu z-index 관련 해결
}
