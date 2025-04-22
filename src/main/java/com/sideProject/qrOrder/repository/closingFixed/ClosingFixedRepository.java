package com.sideProject.qrOrder.repository.closingFixed;

import com.sideProject.qrOrder.entity.ClosingFixed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClosingFixedRepository extends JpaRepository<ClosingFixed, Long>, ClosingFixedCustomRepository {
}
