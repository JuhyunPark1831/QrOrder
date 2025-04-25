package com.sideProject.qrOrder.repository.menuOptionGroup;

import com.sideProject.qrOrder.entity.MenuOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuOptionGroupRepository extends JpaRepository<MenuOptionGroup, Long>, MenuOptionGroupCustomRepository {

    public Optional<MenuOptionGroup> findByOgName(String ogName);
}
