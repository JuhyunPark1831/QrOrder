package com.sideproject.qrOrder.repository;

import com.sideproject.qrOrder.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
