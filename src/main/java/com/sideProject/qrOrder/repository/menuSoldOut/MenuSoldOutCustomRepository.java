package com.sideProject.qrOrder.repository.menuSoldOut;

import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.entity.Menu;
import com.sideProject.qrOrder.entity.MenuSoldOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuSoldOutCustomRepository {

    public List<MenuSoldOut> findByMsStatusAndDateTimeAfter(SoldOutStatus msStatus);
}
