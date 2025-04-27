package com.sideProject.qrOrder.repository.menuOptionGroupJunction;

import com.sideProject.qrOrder.entity.MenuOption;
import com.sideProject.qrOrder.entity.MenuOptionGroupJunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MenuOptionGroupJunctionRepository extends JpaRepository<MenuOptionGroupJunction, Long> {

    List<MenuOptionGroupJunction> findByMjOg_OgId(Long ogId);
    List<MenuOptionGroupJunction> findByMjMe_MeId(Long meId);
    public void deleteByMjOg_OgId(Long ogId);
    public void deleteByMjMe_MeId(Long meId);
}
