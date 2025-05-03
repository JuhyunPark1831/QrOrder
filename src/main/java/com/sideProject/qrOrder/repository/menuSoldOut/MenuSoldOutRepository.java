package com.sideProject.qrOrder.repository.menuSoldOut;

import com.sideProject.qrOrder.entity.MenuSoldOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuSoldOutRepository extends JpaRepository<MenuSoldOut, Long> {

    public Optional<MenuSoldOut> findByMsMe_MeId(Long meId);
    public void deleteByMsMe_MeId(Long meId);
}
