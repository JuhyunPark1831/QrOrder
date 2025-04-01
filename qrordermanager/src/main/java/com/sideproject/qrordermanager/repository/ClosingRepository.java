package com.sideproject.qrordermanager.repository;

import com.sideproject.qrordermanager.entity.Closing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClosingRepository extends JpaRepository<Closing, Long> {
}
