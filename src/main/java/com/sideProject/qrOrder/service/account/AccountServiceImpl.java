package com.sideproject.qrOrder.service.account;

import com.sideproject.qrOrder.common.error.ApiCustomException;
import com.sideproject.qrOrder.common.error.ErrorCode;
import com.sideproject.qrOrder.common.util.CookieUtil;
import com.sideproject.qrOrder.common.util.JwtTokenProvider;
import com.sideproject.qrOrder.common.util.JwtUtil;
import com.sideproject.qrOrder.common.util.RedisUtil;
import com.sideproject.qrOrder.dto.account.request.LoginRequestDto;
import com.sideproject.qrOrder.dto.account.response.JwtResponseDto;
import com.sideproject.qrOrder.entity.Account;
import com.sideproject.qrOrder.repository.AccountRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final CookieUtil cookieUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void login(HttpServletResponse response, LoginRequestDto requestDto) {

        Account loginAccount = accountRepository.findByAcLoginId(requestDto.getAcLoginId()).orElseThrow(() ->
                new ApiCustomException(ErrorCode.NOT_FOUND_ACCOUNT));

        if (!passwordEncoder.matches(requestDto.getAcPassword(), loginAccount.getAcPassword())) {
            throw new ApiCustomException(ErrorCode.INCORRECT_PASSWORD);
        }

        JwtResponseDto tokens = JwtResponseDto.builder()
                .accessToken(jwtTokenProvider.createAccessToken(requestDto.getAcLoginId()))
                .refreshToken(jwtTokenProvider.createRefreshToken(requestDto.getAcLoginId()))
                .build();

        cookieUtil.setToken(response, tokens);
    }

    @Override
    public JwtResponseDto refresh(String refreshToken) {

        jwtUtil.validateToken(refreshToken);
        String acLoginId = jwtUtil.getAcLoginIdFromToken(refreshToken);

        if (!Objects.equals(redisUtil.getData(acLoginId), refreshToken)) {
            throw new ApiCustomException(ErrorCode.REFRESH_TOKEN_AUTHENTICATION_FAILED);
        }

        return JwtResponseDto.builder()
                .accessToken(jwtTokenProvider.createAccessToken(acLoginId))
                .refreshToken(jwtTokenProvider.createRefreshToken(acLoginId))
                .build();
    }

    @Override
    @Transactional
    public void createAccountForAdmin() {
        accountRepository.save(Account.builder()
                        .acLoginId("admin123")
                        .acPassword(passwordEncoder.encode("`1q`1q`1q"))
                        .acName("최초관리자")
                .build());
    }
}
