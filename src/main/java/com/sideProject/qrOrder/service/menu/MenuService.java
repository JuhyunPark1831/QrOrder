package com.sideProject.qrOrder.service.menu;

import com.sideProject.qrOrder.dto.client.CartInfoRequestDto;
import com.sideProject.qrOrder.dto.client.CartInfoResponseDto;
import com.sideProject.qrOrder.dto.client.MenuClientDto;
import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuService {

    public void createMenu(MenuDto requestDto);
    public Page<MenuDto> selectMenu(Pageable pageable, MenuDto requestDto);
    public MenuDto selectMenuDetail(Long meId);
    public MenuClientDto selectMenuDetailClient(Long meId);
    public List<CartInfoResponseDto> getCartInfo(List<CartInfoRequestDto> requestDtoList);
    public void modifyMenu(MenuDto requestDto);
    public void modifyMeStatus(Long meId, SoldOutStatus meStatus);
    public void deleteMenu(MenuDto requestDto);
}
