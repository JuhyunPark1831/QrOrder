package com.sideProject.qrOrder.repository;

import com.sideProject.qrOrder.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByMeCa_CaId(Long caId);
}
