package com.sideproject.qrOrder.controller.account;

import com.sideproject.qrOrder.common.response.ApiResponse;
import com.sideproject.qrOrder.dto.account.request.LoginRequestDto;
import com.sideproject.qrOrder.service.account.AccountService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountRestController {

    public final AccountService accountService;

    @PostMapping("/login")
    public ApiResponse<String> loginProc (HttpServletResponse response, @RequestBody LoginRequestDto requestDto) {

        accountService.login(response, requestDto);

        return ApiResponse.ok("로그인 성공");
    }

    @GetMapping("/createAdmin")
    public void createAccountForAdmin() {
        accountService.createAccountForAdmin();
    }
}
