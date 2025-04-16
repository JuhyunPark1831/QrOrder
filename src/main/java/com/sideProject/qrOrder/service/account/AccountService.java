package com.sideProject.qrOrder.service.account;

import com.sideProject.qrOrder.dto.account.request.AccountRequestDto;
import com.sideProject.qrOrder.dto.account.response.AccountResponseDto;
import com.sideProject.qrOrder.dto.account.response.JwtResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    // 인증
    public void login(HttpServletResponse response, AccountRequestDto requestDto);
    public JwtResponseDto refresh(String refreshToken);

    // 계정 관리
    public void createAccount(AccountRequestDto requestDto);
    public Page<AccountResponseDto> selectAccount(Pageable pageable, AccountRequestDto requestDto);
    public void deleteAccount(List<Long> acIds);
    public void checkAcLoginId(AccountRequestDto requestDto);

    public void createAccountForAdmin(); // 추후 삭제
}
