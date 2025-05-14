package com.sideProject.qrOrder.service.menuOption;

import com.sideProject.qrOrder.dto.client.MenuClientDto;
import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuOptionService {

    public void modifyOpStatus(Long opId, SoldOutStatus meStatus);
}
