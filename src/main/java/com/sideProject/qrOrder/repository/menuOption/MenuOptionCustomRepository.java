package com.sideProject.qrOrder.repository.menuOption;

import com.sideProject.qrOrder.dto.client.MenuOptionClientDto;
import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutDto;

import java.util.List;

public interface MenuOptionCustomRepository {

    public long countNotSoldOutMenuOptions(Long ogId);
    public List<MenuOptionSoldOutDto> findMenuOptionSoldOutDtoListByOgId(Long ogId);
    public List<MenuOptionClientDto> findMenuOptionClientDtoListByOgId(Long ogId);
}
