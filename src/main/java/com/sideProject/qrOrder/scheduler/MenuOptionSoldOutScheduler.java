package com.sideProject.qrOrder.scheduler;

import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutProgressStatus;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.entity.Menu;
import com.sideProject.qrOrder.entity.MenuOption;
import com.sideProject.qrOrder.entity.MenuOptionSoldOut;
import com.sideProject.qrOrder.entity.MenuSoldOut;
import com.sideProject.qrOrder.repository.menu.MenuRepository;
import com.sideProject.qrOrder.repository.menuOption.MenuOptionRepository;
import com.sideProject.qrOrder.repository.menuOptionSoldOut.MenuOptionSoldOutRepository;
import com.sideProject.qrOrder.repository.menuSoldOut.MenuSoldOutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuOptionSoldOutScheduler {

    private final MenuSoldOutRepository menuSoldOutRepository;
    private final MenuRepository menuRepository;

    private final MenuOptionSoldOutRepository menuOptionSoldOutRepository;
    private final MenuOptionRepository menuOptionRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void checkMenuOptionSoldOut() {

        activateScheduledSoldOutMenuOptions();
        deactivateExpiredSoldOutMenuOptions();
    }

    private void activateScheduledSoldOutMenuOptions() {

        List<MenuOptionSoldOut> menuOptionSoldOutList = menuOptionSoldOutRepository.findByOsStatusAndDateTimeAfter(SoldOutProgressStatus.WAITING);

        menuOptionSoldOutList.forEach(menuOptionSoldOut -> menuOptionSoldOut.modifyOsStatus(SoldOutProgressStatus.SOLD_OUT));
        List<Long> menuOptionIds = menuOptionSoldOutList.stream()
                .map(os -> os.getOsOp().getOpId())
                .toList();

        List<MenuOption> menuOptions = menuOptionRepository.findAllById(menuOptionIds);
        menuOptions.forEach(menuOption -> menuOption.modifyOpStatus(SoldOutStatus.SOLD_OUT));
    }

    private void deactivateExpiredSoldOutMenuOptions() {

        List<MenuOptionSoldOut> menuOptionSoldOutList = menuOptionSoldOutRepository.findByOsStatusAndDateTimeAfter(SoldOutProgressStatus.SOLD_OUT);

        List<Long> menuOptionIds = menuOptionSoldOutList.stream()
                .map(os -> os.getOsOp().getOpId())
                .toList();

        List<MenuOption> menuOptions = menuOptionRepository.findAllById(menuOptionIds);
        menuOptions.forEach(menuOption -> menuOption.modifyOpStatus(SoldOutStatus.AVAILABLE));

        menuOptionSoldOutRepository.deleteAll(menuOptionSoldOutList);
    }
}
