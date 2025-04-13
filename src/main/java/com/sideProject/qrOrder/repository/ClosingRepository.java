package com.sideproject.qrOrder.repository;

import com.sideproject.qrOrder.entity.Closing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClosingRepository extends JpaRepository<Closing, Long> {
}
