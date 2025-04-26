package com.sideProject.qrOrder.service.menu;

import com.sideProject.qrOrder.dto.menu.MenuDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {

    public void createMenu(MenuDto requestDto);
    public Page<MenuDto> selectMenu(Pageable pageable, MenuDto requestDto);
    public void deleteMenu(MenuDto requestDto);
}
