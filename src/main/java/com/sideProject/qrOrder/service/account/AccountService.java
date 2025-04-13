package com.sideproject.qrOrder.service.account;

import com.sideproject.qrOrder.dto.account.request.LoginRequestDto;
import com.sideproject.qrOrder.dto.account.response.JwtResponseDto;
import jakarta.servlet.http.HttpServletResponse;

public interface AccountService {

    public void login(HttpServletResponse response, LoginRequestDto requestDto);
    public JwtResponseDto refresh(String refreshToken);

    public void createAccountForAdmin(); // 추후 삭제
}
