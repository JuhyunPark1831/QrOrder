package com.sideProject.qrOrder.service.account;

import com.sideProject.qrOrder.common.error.ApiCustomException;
import com.sideProject.qrOrder.common.error.ErrorCode;
import com.sideProject.qrOrder.common.util.CookieUtil;
import com.sideProject.qrOrder.common.util.JwtTokenProvider;
import com.sideProject.qrOrder.common.util.JwtUtil;
import com.sideProject.qrOrder.common.util.RedisUtil;
import com.sideProject.qrOrder.dto.account.AccountRequestDto;
import com.sideProject.qrOrder.dto.account.AccountResponseDto;
import com.sideProject.qrOrder.dto.account.JwtResponseDto;
import com.sideProject.qrOrder.entity.Account;
import com.sideProject.qrOrder.repository.account.AccountRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public void login(AccountRequestDto requestDto, HttpServletResponse response) {

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
    public void createAccount(AccountRequestDto requestDto) {

        checkAcLoginId(requestDto);

        accountRepository.save(Account.builder()
                .acLoginId(requestDto.getAcLoginId())
                .acName(requestDto.getAcName())
                .acPassword(passwordEncoder.encode(requestDto.getAcPassword()))
                .build());
    }

    @Override
    public Page<AccountResponseDto> selectAccount(Pageable pageable, AccountRequestDto requestDto) {

        Page<Account> accountPage = accountRepository.findAccount(pageable, requestDto);

        return accountPage.map(AccountResponseDto :: from);
    }

    @Override
    @Transactional
    public void deleteAccount(List<Long> acIds) {
        accountRepository.deleteAllById(acIds);
    }

    @Override
    public void checkAcLoginId(AccountRequestDto requestDto) {
        if (accountRepository.findByAcLoginId(requestDto.getAcLoginId()).isPresent()) {
            throw new ApiCustomException(ErrorCode.NOT_UNIQUE_LOGIN_ID);
        }
    }

    @Override
    @Transactional
    public void createAccountForAdmin() {
        accountRepository.save(Account.builder()
                        .acLoginId("")
                        .acPassword(passwordEncoder.encode("`1q`1q`1q"))
                        .acName("최초관리자")
                .build());
    }
}
