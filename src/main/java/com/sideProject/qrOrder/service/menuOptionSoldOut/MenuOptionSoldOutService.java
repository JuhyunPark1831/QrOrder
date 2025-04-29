package com.sideProject.qrOrder.service.menuOptionSoldOut;

import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutDto;
import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface MenuOptionSoldOutService {

    public void createMenuOptionSoldOut(MenuOptionSoldOutDto requestDto);
    public Page<MenuOptionSoldOutResponseDto> selectMenuOptionSoldOut(Pageable pageable, MenuOptionSoldOutDto requestDto);
    public void deleteMenuOptionSoldOut(Long ogId);
}
