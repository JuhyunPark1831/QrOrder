package com.sideProject.qrOrder.repository.account;

import com.sideProject.qrOrder.dto.account.request.AccountRequestDto;
import com.sideProject.qrOrder.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountCustomRepository {

    public Page<Account> findAccount(Pageable pageable, AccountRequestDto requestDto);
}
