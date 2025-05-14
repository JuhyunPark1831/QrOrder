package com.sideProject.qrOrder.repository.menuOptionSoldOut;

import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutProgressStatus;
import com.sideProject.qrOrder.entity.MenuOptionSoldOut;
import com.sideProject.qrOrder.entity.MenuSoldOut;

import java.util.List;

public interface MenuOptionSoldOutCustomRepository {

    public List<MenuOptionSoldOut> findByOsStatusAndDateTimeAfter(SoldOutProgressStatus osStatus);
}
