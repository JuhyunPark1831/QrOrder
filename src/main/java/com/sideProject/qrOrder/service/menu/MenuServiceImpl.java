package com.sideProject.qrOrder.service.menu;

import com.sideProject.qrOrder.common.error.ApiCustomException;
import com.sideProject.qrOrder.common.error.ErrorCode;
import com.sideProject.qrOrder.common.error.ViewCustomException;
import com.sideProject.qrOrder.common.util.FileUtil;
import com.sideProject.qrOrder.dto.MenuOptionGroupJunctionDto;
import com.sideProject.qrOrder.dto.client.*;
import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.entity.Menu;
import com.sideProject.qrOrder.entity.MenuOption;
import com.sideProject.qrOrder.entity.MenuOptionGroup;
import com.sideProject.qrOrder.entity.MenuOptionGroupJunction;
import com.sideProject.qrOrder.repository.menu.MenuRepository;
import com.sideProject.qrOrder.repository.category.CategoryRepository;
import com.sideProject.qrOrder.repository.menuOption.MenuOptionRepository;
import com.sideProject.qrOrder.repository.menuOptionGroup.MenuOptionGroupRepository;
import com.sideProject.qrOrder.repository.menuOptionGroupJunction.MenuOptionGroupJunctionRepository;
import com.sideProject.qrOrder.repository.menuSoldOut.MenuSoldOutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {

    @Value("${spring.dir.menuImagePath}")
    private String path;

    private final MenuSoldOutRepository menuSoldOutRepository;
    private final CategoryRepository categoryRepository;
    private final MenuOptionGroupJunctionRepository menuOptionGroupJunctionRepository;
    private final MenuOptionGroupRepository menuOptionGroupRepository;
    private final MenuOptionRepository menuOptionRepository;
    private final MenuRepository menuRepository;

    private final FileUtil fileUtil;

    @Override
    @Transactional
    public void createMenu(MenuDto requestDto) {

        Menu menu = menuRepository.save(Menu.builder()
                .meName(requestDto.getMeName())
                .mePrice(requestDto.getMePrice())
                .meStatus(SoldOutStatus.AVAILABLE)
                .meDescription(requestDto.getMeDescription())
                .meImagePath(requestDto.getMeImage() == null ?
                        null : fileUtil.saveImage(requestDto.getMeImage(), path + File.separator + requestDto.getMeName()))
                .meCa(categoryRepository.findById(requestDto.getMeCaId()).orElseThrow(() ->
                        new ApiCustomException(ErrorCode.NOT_FOUND_CATEGORY)))
                .build());

        if (requestDto.getMenuOptionGroupJunctionDtoList() != null) {
            int seq = 1;
            for (MenuOptionGroupJunctionDto menuOptionGroupJunctionDto : requestDto.getMenuOptionGroupJunctionDtoList()) {
                menuOptionGroupJunctionRepository.save(MenuOptionGroupJunction.builder()
                        .mjSeq(seq++)
                        .mjOg(menuOptionGroupRepository.findById(menuOptionGroupJunctionDto.getOgId()).orElseThrow( () ->
                                new ApiCustomException(ErrorCode.NOT_FOUND_MENU_OPTION_GROUP)))
                        .mjMe(menu)
                        .build());
            }
        }
    }

    @Override
    public Page<MenuDto> selectMenu(Pageable pageable, MenuDto requestDto) {

        Page<Menu> menuList = menuRepository.findMenu(pageable, requestDto);

        return menuList.map(MenuDto :: from);
    }

    @Override
    public MenuDto selectMenuDetail(Long meId) {

        Menu menu = menuRepository.findById(meId).orElseThrow(() ->
                new ViewCustomException(ErrorCode.NOT_FOUND_MENU));

        List<MenuOptionGroupJunction> menuOptionGroupJunctionList = menuOptionGroupJunctionRepository.findByMjMe_MeId(menu.getMeId());

        return MenuDto.builder()
                .meId(menu.getMeId())
                .meName(menu.getMeName())
                .meCaId(menu.getMeCa().getCaId())
                .mePrice(menu.getMePrice())
                .meDescription(menu.getMeDescription())
                .meImagePath(menu.getMeImagePath())
                .menuOptionGroupJunctionDtoList(menuOptionGroupJunctionList.stream()
                        .map(menuOptionGroupJunction -> MenuOptionGroupJunctionDto.builder()
                                .mjId(menuOptionGroupJunction.getMjId())
                                .ogId(menuOptionGroupJunction.getMjOg().getOgId())
                                .build())
                        .toList())
                .build();
    }

    @Override
    public MenuClientDto selectMenuDetailClient(Long meId) {

        Menu menu = menuRepository.findById(meId).orElseThrow(() ->
                new ViewCustomException(ErrorCode.NOT_FOUND_MENU));

        List<MenuOptionGroup> menuOptionGroupList = menuOptionGroupJunctionRepository.findByMjMe_MeId(menu.getMeId()).stream()
                .map(MenuOptionGroupJunction::getMjOg)
                .toList();

        List<MenuOptionGroupClientDto> menuOptionGroupClientDtoList = menuOptionGroupList.stream()
                .map(menuOptionGroup -> MenuOptionGroupClientDto.builder()
                        .ogId(menuOptionGroup.getOgId())
                        .ogName(menuOptionGroup.getOgName())
                        .ogMinSelect(menuOptionGroup.getOgMinSelect())
                        .ogMaxSelect(menuOptionGroup.getOgMaxSelect())
                        .menuOptionClientDtoList(menuOptionRepository.findMenuOptionClientDtoListByOgId(menuOptionGroup.getOgId()))
                        .build()).toList();

        return MenuClientDto.builder()
                .meId(menu.getMeId())
                .meName(menu.getMeName())
                .mePrice(menu.getMePrice())
                .meDescription(menu.getMeDescription())
                .meImagePath(menu.getMeImagePath())
                .menuOptionGroupClientDtoList(menuOptionGroupClientDtoList)
                .build();
    }

    @Override
    public List<CartInfoResponseDto> getCartInfo(List<CartInfoRequestDto> requestDtoList) {

        List<CartInfoResponseDto> result = new ArrayList<>();

        for (CartInfoRequestDto requestDto : requestDtoList) {

            Menu menu = menuRepository.findById(requestDto.getMeId()).orElseThrow(() ->
                            new ApiCustomException(ErrorCode.NOT_FOUND_MENU));

            int totalPrice = menuOptionRepository.findAllById(requestDto.getOpIds()).stream()
                    .mapToInt(MenuOption::getOpPrice)
                    .sum();

            result.add(CartInfoResponseDto.builder()
                            .meId(menu.getMeId())
                            .meName(menu.getMeName())
                            .mePrice(menu.getMePrice())
                            .menuOptionGroupClientDtoList(getMenuOptionCartInfo(menu.getMeId(), requestDto.getOpIds()))
                            .totalPrice(totalPrice)
                            .quantity(requestDto.getQuantity())
                    .build());
        }

        return result;
    }

    @Override
    @Transactional
    public void modifyMenu(MenuDto requestDto) {

        Menu menu = menuRepository.findById(requestDto.getMeId()).orElseThrow(() ->
                new ApiCustomException(ErrorCode.NOT_FOUND_MENU));

        menu.modify(requestDto, categoryRepository.findById(requestDto.getMeCaId()).orElseThrow(() ->
                new ApiCustomException(ErrorCode.NOT_FOUND_CATEGORY)));

        // 이미지 처리
        if (requestDto.isDeleteImage()) {
            fileUtil.deleteImage(path + File.separator + menu.getMeName());
            menu.modifyMeImagePath(null);
        }
        if (requestDto.getMeImage() != null) {
            fileUtil.deleteImage(path + File.separator + menu.getMeName());
            String imagePath = requestDto.getMeImage() == null ?
                    null : fileUtil.saveImage(requestDto.getMeImage(), path + File.separator + requestDto.getMeName());

            menu.modifyMeImagePath(imagePath);
        }

        // 옵션그룹 처리
        if (requestDto.getMenuOptionGroupJunctionDtoList() != null) {
            int seq = 1;
            for (MenuOptionGroupJunctionDto menuOptionGroupJunctionDto : requestDto.getMenuOptionGroupJunctionDtoList()) {
                if (menuOptionGroupJunctionDto.getMjId() == null) {
                    // 새로운 옵션그룹은 추가
                    menuOptionGroupJunctionRepository.save(MenuOptionGroupJunction.builder()
                            .mjMe(menu)
                            .mjOg(menuOptionGroupRepository.findById(menuOptionGroupJunctionDto.getOgId()).orElseThrow(() ->
                                    new ApiCustomException(ErrorCode.NOT_FOUND_MENU_OPTION_GROUP)))
                            .mjSeq(seq++)
                            .build());
                } else {
                    // 기존 옵션그룹은 수정
                    MenuOptionGroupJunction menuOptionGroupJunction = menuOptionGroupJunctionRepository.findById(menuOptionGroupJunctionDto.getMjId()).orElseThrow(() ->
                            new ApiCustomException(ErrorCode.NOT_FOUND_MENU_OPTION_GROUP_JUNCTION));

                    menuOptionGroupJunction.modify(menu, menuOptionGroupRepository.findById(menuOptionGroupJunctionDto.getOgId()).orElseThrow(() ->
                            new ApiCustomException(ErrorCode.NOT_FOUND_MENU_OPTION_GROUP)), seq++);
                }
            }
        }

        // 삭제된 옵션 그룹
        menuOptionGroupJunctionRepository.deleteAllById(requestDto.getDeleteMjIds());
    }

    @Override
    @Transactional
    public void modifyMeStatus(Long meId, SoldOutStatus meStatus) {

        Menu menu = menuRepository.findById(meId).orElseThrow(() ->
                new ApiCustomException(ErrorCode.NOT_FOUND_MENU));

        menu.modifyMeStatus(meStatus);

        menuSoldOutRepository.deleteByMsMe_MeId(meId);
    }

    @Override
    @Transactional
    public void deleteMenu(MenuDto requestDto) {

        menuOptionGroupJunctionRepository.deleteByMjMe_MeId(requestDto.getMeId());
        menuRepository.deleteById(requestDto.getMeId());
    }

    public List<MenuOptionGroupClientDto> getMenuOptionCartInfo(Long menuId, List<Long> opIds) {

        List<MenuOption> menuOptionList = menuOptionRepository.findAllById(opIds);;

        Map<Long, List<MenuOption>> groupedByGroupId = menuOptionList.stream()
                .collect(Collectors.groupingBy(opt -> opt.getOpOg().getOgId()));

        List<MenuOptionGroupJunction> junctions = menuOptionGroupJunctionRepository.findByMjMe_MeId(menuId);

        return junctions.stream()
                .sorted(Comparator.comparingInt(MenuOptionGroupJunction::getMjSeq))
                .map(junction -> {
                    MenuOptionGroup menuOptionGroup = junction.getMjOg();
                    Long ogId = menuOptionGroup.getOgId();

                    List<MenuOption> optionsInGroup = groupedByGroupId.get(ogId);

                    if (optionsInGroup == null || optionsInGroup.isEmpty()) {
                        return null;
                    }

                    List<MenuOptionClientDto> menuOptionClientDtos = optionsInGroup.stream()
                            .map(option -> MenuOptionClientDto.builder()
                                    .opId(option.getOpId())
                                    .opName(option.getOpName())
                                    .opPrice(option.getOpPrice())
                                    .build())
                            .toList();

                    return MenuOptionGroupClientDto.builder()
                            .ogId(ogId)
                            .ogName(menuOptionGroup.getOgName())
                            .menuOptionClientDtoList(menuOptionClientDtos)
                            .build();
                })
                .filter(Objects::nonNull)
                .toList();
    }
}
