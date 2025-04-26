package com.sideProject.qrOrder.service.menu;

import com.sideProject.qrOrder.common.error.ApiCustomException;
import com.sideProject.qrOrder.common.error.ErrorCode;
import com.sideProject.qrOrder.common.util.FileUtil;
import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.entity.Common.ENUM.MenuStatus;
import com.sideProject.qrOrder.entity.Menu;
import com.sideProject.qrOrder.entity.MenuOptionGroupJunction;
import com.sideProject.qrOrder.repository.menu.MenuRepository;
import com.sideProject.qrOrder.repository.category.CategoryRepository;
import com.sideProject.qrOrder.repository.menuOptionGroup.MenuOptionGroupRepository;
import com.sideProject.qrOrder.repository.menuOptionGroupJunction.MenuOptionGroupJunctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {

    @Value("${spring.dir.menuImagePath}")
    private String path;

    private final CategoryRepository categoryRepository;
    private final MenuOptionGroupJunctionRepository menuOptionGroupJunctionRepository;
    private final MenuOptionGroupRepository menuOptionGroupRepository;
    private final MenuRepository menuRepository;

    private final FileUtil fileUtil;

    @Override
    @Transactional
    public void createMenu(MenuDto requestDto) {

        Menu menu = menuRepository.save(Menu.builder()
                .meName(requestDto.getMeName())
                .mePrice(requestDto.getMePrice())
                .meStatus(MenuStatus.AVAILABLE)
                .meDescription(requestDto.getMeDescription())
                .meImagePath(requestDto.getMeImage() == null ?
                        null : fileUtil.saveImage(requestDto.getMeImage(), path + File.separator + requestDto.getMeName()))
                .meCa(categoryRepository.findById(requestDto.getMeCaId()).orElseThrow(() ->
                        new ApiCustomException(ErrorCode.NOT_FOUND_CATEGORY)))
                .build());

        int seq = 1;
        for (Long ogId : requestDto.getOgIdList()) {
            menuOptionGroupJunctionRepository.save(MenuOptionGroupJunction.builder()
                    .mjSeq(seq++)
                    .mjOg(menuOptionGroupRepository.findById(ogId).orElseThrow( () ->
                                    new ApiCustomException(ErrorCode.NOT_FOUND_MENU_OPTION_GROUP)))
                    .mjMe(menu)
                    .build());
        }
    }

    @Override
    public Page<MenuDto> selectMenu(Pageable pageable, MenuDto requestDto) {

        Page<Menu> menuList = menuRepository.findMenu(pageable, requestDto);

        return menuList.map(MenuDto :: from);
    }
}
