package com.sideProject.qrOrder.controller.account;

import com.sideProject.qrOrder.common.response.ApiResponse;
import com.sideProject.qrOrder.dto.account.request.AccountRequestDto;
import com.sideProject.qrOrder.service.account.AccountService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountRestController {

    public final AccountService accountService;

    @PostMapping("/login")
    public ApiResponse<String> loginProc(HttpServletResponse response, @RequestBody AccountRequestDto requestDto) {

        accountService.login(response, requestDto);

        return ApiResponse.ok("로그인 성공");
    }

    @PostMapping("/create")
    public ApiResponse<String> createAccount(@RequestBody AccountRequestDto requestDto) {

        accountService.createAccount(requestDto);

        return ApiResponse.ok("계정 등록 완료");
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteAccount(@RequestBody List<Long> acIds) {

        accountService.deleteAccount(acIds);

        return ApiResponse.ok("계정 삭제 완료");
    }

    @PostMapping("/check")
    public ApiResponse<String> checkAcLoginId(@RequestBody AccountRequestDto requestDto) {

        accountService.checkAcLoginId(requestDto);

        return ApiResponse.ok("중복 확인 완료");
    }

    @GetMapping("/createAdmin")
    public void createAccountForAdmin() {
        accountService.createAccountForAdmin();
    }
}
