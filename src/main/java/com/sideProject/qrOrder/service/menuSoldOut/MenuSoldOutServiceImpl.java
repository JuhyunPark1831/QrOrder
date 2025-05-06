package com.sideProject.qrOrder.service.menuSoldOut;

import com.sideProject.qrOrder.common.error.ApiCustomException;
import com.sideProject.qrOrder.common.error.ErrorCode;
import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.dto.menuSoldOut.MenuSoldOutDto;
import com.sideProject.qrOrder.entity.Common.ENUM.MenuStatus;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.entity.Menu;
import com.sideProject.qrOrder.entity.MenuSoldOut;
import com.sideProject.qrOrder.repository.menu.MenuRepository;
import com.sideProject.qrOrder.repository.menuSoldOut.MenuSoldOutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuSoldOutServiceImpl implements MenuSoldOutService {

    private final MenuRepository menuRepository;
    private final MenuSoldOutRepository menuSoldOutRepository;

    @Override
    @Transactional
    public void createMenuSoldOut(MenuSoldOutDto requestDto) {

        Menu menu = menuRepository.findById(requestDto.getMsMeId()).orElseThrow(() ->
                new ApiCustomException(ErrorCode.NOT_FOUND_MENU));

        menuSoldOutRepository.deleteByMsMe_MeId(requestDto.getMsMeId());

        menuSoldOutRepository.save(MenuSoldOut.builder()
                .msStart(requestDto.getMsStart())
                .msEnd(requestDto.getMsEnd())
                .msMe(menu)
                .msStatus(SoldOutStatus.WAITING)
                .build());
    }

    @Override
    public Page<MenuSoldOutDto> selectMenuSoldOut(Pageable pageable, MenuSoldOutDto requestDto) {

        Page<Menu> menuPage = menuRepository.findMenu(pageable, MenuDto.builder()
                        .searchWord(requestDto == null ? null : requestDto.getSearchWord()).build());

        return menuPage.map(menu -> MenuSoldOutDto.builder()
                .msMeId(menu.getMeId())
                .meName(menu.getMeName())
                .meCaId(menu.getMeCa().getCaId())
                .meStatus(menu.getMeStatus())
                .menuSoldOut(menuSoldOutRepository.findByMsMe_MeId(menu.getMeId()).orElse(null))
                .build());

        //todo: 스케줄러 품절 취소 처리
    }

    @Override
    @Transactional
    public void deleteMenuSoldOut(Long msId) {
        menuSoldOutRepository.deleteById(msId);
    }
}
