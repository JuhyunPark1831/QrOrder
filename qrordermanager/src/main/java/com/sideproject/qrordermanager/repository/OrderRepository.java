package com.sideproject.qrordermanager.repository;

import com.sideproject.qrordermanager.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
