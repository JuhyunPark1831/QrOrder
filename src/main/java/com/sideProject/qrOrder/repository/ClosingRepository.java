package com.sideProject.qrOrder.repository;

import com.sideProject.qrOrder.entity.Closing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClosingRepository extends JpaRepository<Closing, Long> {
}
