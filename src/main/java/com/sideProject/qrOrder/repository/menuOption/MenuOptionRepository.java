package com.sideProject.qrOrder.repository.menuOption;

import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.entity.Menu;
import com.sideProject.qrOrder.entity.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long>, MenuOptionCustomRepository {

    public List<MenuOption> findByOpOg_OgIdIn(List<Long> ogIds);
    long countByOpOg_OgId(Long ogId);
    public void deleteByOpOg_OgId(Long ogId);
}
