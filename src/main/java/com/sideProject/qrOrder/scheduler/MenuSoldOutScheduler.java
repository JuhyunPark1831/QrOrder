package com.sideProject.qrOrder.scheduler;

import com.sideProject.qrOrder.common.error.ApiCustomException;
import com.sideProject.qrOrder.common.error.ErrorCode;
import com.sideProject.qrOrder.entity.Common.ENUM.MenuStatus;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.entity.Menu;
import com.sideProject.qrOrder.entity.MenuSoldOut;
import com.sideProject.qrOrder.repository.menu.MenuRepository;
import com.sideProject.qrOrder.repository.menuSoldOut.MenuSoldOutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuSoldOutScheduler {

    private final MenuSoldOutRepository menuSoldOutRepository;
    private final MenuRepository menuRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void checkMenuSoldOut() {

        activateScheduledSoldOutMenus();
        deactivateExpiredSoldOutMenus();
    }

    private void activateScheduledSoldOutMenus() {

        List<MenuSoldOut> menuSoldOutList = menuSoldOutRepository.findByMsStatusAndDateTimeAfter(SoldOutStatus.WAITING);

        menuSoldOutList.forEach(menuSoldOut -> menuSoldOut.modifyMsStatus(SoldOutStatus.SOLD_OUT));
        List<Long> menuIds = menuSoldOutList.stream()
                .map(ms -> ms.getMsMe().getMeId())
                .toList();

        List<Menu> menus = menuRepository.findAllById(menuIds);
        menus.forEach(menu -> menu.modifyMeStatus(MenuStatus.SOLD_OUT));
    }

    private void deactivateExpiredSoldOutMenus() {

        List<MenuSoldOut> menuSoldOutList = menuSoldOutRepository.findByMsStatusAndDateTimeAfter(SoldOutStatus.SOLD_OUT);

        List<Long> menuIds = menuSoldOutList.stream()
                .map(ms -> ms.getMsMe().getMeId())
                .toList();

        List<Menu> menus = menuRepository.findAllById(menuIds);
        menus.forEach(menu -> menu.modifyMeStatus(MenuStatus.AVAILABLE));

        menuSoldOutRepository.deleteAll(menuSoldOutList);
    }
}
