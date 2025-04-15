package com.sideProject.qrOrder.repository;

import com.sideProject.qrOrder.entity.MenuOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOrderRepository extends JpaRepository<MenuOrder, Long> {
}
