package com.sideproject.qrordermanager.repository;

import com.sideproject.qrordermanager.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
