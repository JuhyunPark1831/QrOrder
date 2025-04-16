package com.sideProject.qrOrder.repository.account;

import com.sideProject.qrOrder.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountCustomRepository {
    Optional<Account> findByAcLoginId(String acLoginId);
}
