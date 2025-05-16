package com.sideProject.qrOrder.scheduler;

import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutProgressStatus;
import com.sideProject.qrOrder.entity.Menu;
import com.sideProject.qrOrder.entity.MenuSoldOut;
import com.sideProject.qrOrder.repository.menu.MenuRepository;
import com.sideProject.qrOrder.repository.menuSoldOut.MenuSoldOutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

        List<MenuSoldOut> menuSoldOutList = menuSoldOutRepository.findByMsStatusAndDateTimeAfter(SoldOutProgressStatus.WAITING);

        menuSoldOutList.forEach(menuSoldOut -> menuSoldOut.modifyMsStatus(SoldOutProgressStatus.SOLD_OUT));
        List<Long> menuIds = menuSoldOutList.stream()
                .map(ms -> ms.getMsMe().getMeId())
                .toList();

        List<Menu> menus = menuRepository.findAllById(menuIds);
        menus.forEach(menu -> menu.modifyMeStatus(SoldOutStatus.SOLD_OUT));
    }

    private void deactivateExpiredSoldOutMenus() {

        List<MenuSoldOut> menuSoldOutList = menuSoldOutRepository.findByMsStatusAndDateTimeAfter(SoldOutProgressStatus.SOLD_OUT);

        List<Long> menuIds = menuSoldOutList.stream()
                .map(ms -> ms.getMsMe().getMeId())
                .toList();

        List<Menu> menus = menuRepository.findAllById(menuIds);
        menus.forEach(menu -> menu.modifyMeStatus(SoldOutStatus.AVAILABLE));

        menuSoldOutRepository.deleteAll(menuSoldOutList);
    }
}
