package com.sideproject.qrordermanager.repository;

import com.sideproject.qrordermanager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAcLoginId(String acLoginId);
}
