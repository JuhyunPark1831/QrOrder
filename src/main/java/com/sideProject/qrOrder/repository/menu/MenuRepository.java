package com.sideProject.qrOrder.repository.menu;

import com.sideProject.qrOrder.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuCustomRepository {
    List<Menu> findByMeCa_CaId(Long caId);
}
