package com.sideProject.qrOrder.repository;

import com.sideProject.qrOrder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
