package com.sideProject.qrOrder.repository.menuOptionSoldOut;

import com.sideProject.qrOrder.entity.MenuOptionSoldOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MenuOptionSoldOutRepository extends JpaRepository<MenuOptionSoldOut, Long>, MenuOptionSoldOutCustomRepository {
    public long countByOsOp_OpOg_OgId(Long ogId);
    public void deleteByOsOp_OpId(Long osId);
}
