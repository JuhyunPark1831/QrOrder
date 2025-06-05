package com.sideProject.qrOrder.service.menuOptionSoldOut;

import com.sideProject.qrOrder.common.error.ApiCustomException;
import com.sideProject.qrOrder.common.error.ErrorCode;
import com.sideProject.qrOrder.dto.menuOptionGroup.MenuOptionGroupDto;
import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutDto;
import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutListDto;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutProgressStatus;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.entity.MenuOption;
import com.sideProject.qrOrder.entity.MenuOptionGroup;
import com.sideProject.qrOrder.entity.MenuOptionSoldOut;
import com.sideProject.qrOrder.repository.menuOption.MenuOptionRepository;
import com.sideProject.qrOrder.repository.menuOptionGroup.MenuOptionGroupRepository;
import com.sideProject.qrOrder.repository.menuOptionSoldOut.MenuOptionSoldOutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuOptionSoldOutServiceImpl implements MenuOptionSoldOutService {

    private final MenuOptionGroupRepository menuOptionGroupRepository;
    private final MenuOptionRepository menuOptionRepository;
    private final MenuOptionSoldOutRepository menuOptionSoldOutRepository;

    @Override
    @Transactional
    public void createMenuOptionSoldOut(MenuOptionSoldOutDto requestDto) {

        MenuOption menuOption = menuOptionRepository.findById(requestDto.getOsOpId()).orElseThrow(() ->
                new ApiCustomException(ErrorCode.NOT_FOUND_MENU_OPTION));

        menuOptionSoldOutRepository.deleteByOsOp_OpId(requestDto.getOsOpId());

        MenuOptionGroup menuOptionGroup = menuOption.getOpOg();
        if (menuOptionRepository.countNotSoldOutMenuOptions(menuOptionGroup.getOgId()) - 1 < menuOptionGroup.getOgMinSelect()) {
            throw new ApiCustomException(ErrorCode.INSUFFICIENT_MENU_OPTION_SELECTION);
        }

        menuOptionSoldOutRepository.save(MenuOptionSoldOut.builder()
                .osStart(requestDto.getOsStart())
                .osEnd(requestDto.getOsEnd())
                .osStatus(SoldOutProgressStatus.WAITING)
                .osOp(menuOption)
                .build());

        //todo: 정오, 자정 처리
        //todo: fixed 날 바뀔 때 추가
    }

    @Override
    public Page<MenuOptionSoldOutListDto> selectMenuOptionSoldOut(Pageable pageable, MenuOptionSoldOutDto requestDto) {

        Page<MenuOptionGroup> menuOptionGroupPage = menuOptionGroupRepository.findMenuOptionGroupList(pageable, MenuOptionGroupDto.builder()
                .searchWord(requestDto == null ? null : requestDto.getSearchWord()).build());

        return menuOptionGroupPage.map(menuOptionGroup -> MenuOptionSoldOutListDto.builder()
                .ogId(menuOptionGroup.getOgId())
                .ogName(menuOptionGroup.getOgName())
                .menuOptionSoldOutDtoList(menuOptionRepository.findMenuOptionSoldOutDtoListByOgId(menuOptionGroup.getOgId()))
                .build());
    }

    @Override
    @Transactional
    public void deleteMenuOptionSoldOut(Long osId) {
        menuOptionSoldOutRepository.deleteById(osId);
    }
}
