package com.sideproject.qrOrder.repository;

import com.sideproject.qrOrder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
