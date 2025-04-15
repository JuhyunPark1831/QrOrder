package com.sideProject.qrOrder.repository;

import com.sideProject.qrOrder.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAcLoginId(String acLoginId);
}
