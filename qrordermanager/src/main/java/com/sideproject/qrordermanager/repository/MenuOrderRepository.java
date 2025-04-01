package com.sideproject.qrordermanager.repository;

import com.sideproject.qrordermanager.entity.MenuOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOrderRepository extends JpaRepository<MenuOrder, Long> {
}
