package com.sideProject.qrOrder.repository.menuOptionGroupJunction;

import com.sideProject.qrOrder.entity.MenuOption;
import com.sideProject.qrOrder.entity.MenuOptionGroupJunction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuOptionGroupJunctionRepository extends JpaRepository<MenuOptionGroupJunction, Long> {

    List<MenuOptionGroupJunction> findByMjOg_OgId(Long ogId);
    public void deleteByMjOg_OgId(Long ogId);
}
