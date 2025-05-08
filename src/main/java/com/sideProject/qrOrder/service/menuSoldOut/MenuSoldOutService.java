package com.sideProject.qrOrder.service.menuSoldOut;

import com.sideProject.qrOrder.dto.menuSoldOut.MenuSoldOutDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuSoldOutService {

    public void createMenuSoldOut(MenuSoldOutDto requestDto);
    public Page<MenuSoldOutDto> selectMenuSoldOut(Pageable pageable, MenuSoldOutDto requestDto);
    public void deleteMenuSoldOut(Long msId);
}
