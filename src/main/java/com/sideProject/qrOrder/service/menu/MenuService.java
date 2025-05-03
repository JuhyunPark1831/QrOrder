package com.sideProject.qrOrder.service.menu;

import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.entity.Common.ENUM.MenuStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {

    public void createMenu(MenuDto requestDto);
    public Page<MenuDto> selectMenu(Pageable pageable, MenuDto requestDto);
    public MenuDto selectMenuDetail(Long meId);
    public void modifyMenu(MenuDto requestDto);
    public void modifyMeStatus(Long meId, MenuStatus meStatus);
    public void deleteMenu(MenuDto requestDto);
}
