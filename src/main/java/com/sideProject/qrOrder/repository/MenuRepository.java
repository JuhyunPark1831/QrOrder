package com.sideProject.qrOrder.repository;

import com.sideProject.qrOrder.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
