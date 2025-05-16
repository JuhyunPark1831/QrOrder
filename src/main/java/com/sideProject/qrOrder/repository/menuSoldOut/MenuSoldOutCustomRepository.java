package com.sideProject.qrOrder.repository.menuSoldOut;

import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutProgressStatus;
import com.sideProject.qrOrder.entity.MenuSoldOut;

import java.util.List;

public interface MenuSoldOutCustomRepository {

    public List<MenuSoldOut> findByMsStatusAndDateTimeAfter(SoldOutProgressStatus msStatus);
}
