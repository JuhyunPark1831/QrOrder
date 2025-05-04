package com.sideProject.qrOrder.service.menuOptionSoldOut;

import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutDto;
import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuOptionSoldOutService {

    public void createMenuOptionSoldOut(MenuOptionSoldOutDto requestDto);
    public Page<MenuOptionSoldOutListDto> selectMenuOptionSoldOut(Pageable pageable, MenuOptionSoldOutDto requestDto);
    public void deleteMenuOptionSoldOut(Long ogId);
}
