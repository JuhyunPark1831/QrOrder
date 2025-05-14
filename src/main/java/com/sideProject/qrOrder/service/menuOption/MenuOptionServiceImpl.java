package com.sideProject.qrOrder.service.menuOption;

import com.sideProject.qrOrder.common.error.ApiCustomException;
import com.sideProject.qrOrder.common.error.ErrorCode;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.entity.MenuOption;
import com.sideProject.qrOrder.repository.menuOption.MenuOptionRepository;
import com.sideProject.qrOrder.repository.menuOptionSoldOut.MenuOptionSoldOutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuOptionServiceImpl implements MenuOptionService {

    private final MenuOptionSoldOutRepository menuOptionSoldOutRepository;
    private final MenuOptionRepository menuOptionRepository;

    @Override
    @Transactional
    public void modifyOpStatus(Long opId, SoldOutStatus meStatus) {

        MenuOption menuOption = menuOptionRepository.findById(opId).orElseThrow(() ->
                new ApiCustomException(ErrorCode.NOT_FOUND_MENU_OPTION));

        menuOption.modifyOpStatus(meStatus);

        menuOptionSoldOutRepository.deleteByOsOp_OpId(opId);
    }
}
