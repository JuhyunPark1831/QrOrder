package com.sideProject.qrOrder.repository.menu;

import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuCustomRepository {

    public Page<Menu> findMenu(Pageable pageable, MenuDto requestDto);
}
