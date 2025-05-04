package com.sideProject.qrOrder.service.account;

import com.sideProject.qrOrder.dto.account.AccountRequestDto;
import com.sideProject.qrOrder.dto.account.AccountResponseDto;
import com.sideProject.qrOrder.dto.account.JwtResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    // 인가, 인증
    public void login(AccountRequestDto requestDto, HttpServletResponse response);
    public JwtResponseDto refresh(String refreshToken);

    // 계정 관리
    public void createAccount(AccountRequestDto requestDto);
    public Page<AccountResponseDto> selectAccount(Pageable pageable, AccountRequestDto requestDto);
    public void deleteAccount(List<Long> acIds);
    public void checkAcLoginId(AccountRequestDto requestDto);

    public void createAccountForAdmin(); // 추후 삭제
}
