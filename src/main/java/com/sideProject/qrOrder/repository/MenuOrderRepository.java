package com.sideproject.qrOrder.repository;

import com.sideproject.qrOrder.entity.MenuOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOrderRepository extends JpaRepository<MenuOrder, Long> {
}
