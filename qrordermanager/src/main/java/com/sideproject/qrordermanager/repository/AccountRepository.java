package com.sideproject.qrordermanager.repository;

import com.sideproject.qrordermanager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
