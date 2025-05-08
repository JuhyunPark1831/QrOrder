package com.sideProject.qrOrder.repository.menuOption;

import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutDto;

import java.util.List;

public interface MenuOptionCustomRepository {

    public List<MenuOptionSoldOutDto> findMenuOptionSoldOutDtoListByOgId(Long ogId);
}
