package com.sideProject.qrOrder.service.menuOptionGroup;

import com.sideProject.qrOrder.common.error.ApiCustomException;
import com.sideProject.qrOrder.common.error.ErrorCode;
import com.sideProject.qrOrder.dto.menuOption.MenuOptionDto;
import com.sideProject.qrOrder.dto.menuOptionGroup.MenuOptionGroupDto;
import com.sideProject.qrOrder.entity.MenuOption;
import com.sideProject.qrOrder.entity.MenuOptionGroup;
import com.sideProject.qrOrder.repository.menuOption.MenuOptionRepository;
import com.sideProject.qrOrder.repository.menuOptionGroup.MenuOptionGroupRepository;
import com.sideProject.qrOrder.repository.menuOptionGroupJunction.MenuOptionGroupJunctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuOptionGroupServiceImpl implements MenuOptionGroupService {

    private final MenuOptionRepository menuOptionRepository;
    private final MenuOptionGroupJunctionRepository menuOptionGroupJunctionRepository;
    private final MenuOptionGroupRepository menuOptionGroupRepository;

    @Override
    @Transactional
    public void createMenuOptionGroup(MenuOptionGroupDto requestDto) {

        if (menuOptionGroupRepository.findByOgName(requestDto.getOgName()).isPresent()) {
            throw new ApiCustomException(ErrorCode.NOT_UNIQUE_MENU_OPTION_GROUP_NAME);
        }

        MenuOptionGroup menuOptionGroup = menuOptionGroupRepository.save(MenuOptionGroup.builder()
                        .ogName(requestDto.getOgName())
                        .ogMinSelect(requestDto.getOgMinSelect())
                        .ogMaxSelect(requestDto.getOgMaxSelect())
                .build());

        for (MenuOptionDto menuOptionDto : requestDto.getMenuOptionDtoList()) {
            menuOptionRepository.save(MenuOption.builder()
                    .opName(menuOptionDto.getOpName())
                    .opPrice(menuOptionDto.getOpPrice())
                    .opOg(menuOptionGroup)
                    .build());
        }
    }

    @Override
    public Page<MenuOptionGroupDto> selectMenuOptionGroup(Pageable pageable, MenuOptionGroupDto requestDto) {

        Page<MenuOptionGroup> menuOptionGroupList = menuOptionGroupRepository.findMenuOptionGroupList(pageable, requestDto);
        List<Long> ogIdList = menuOptionGroupList.stream()
                .map(MenuOptionGroup::getOgId)
                .collect(Collectors.toList());

        List<MenuOption> menuOptionList = menuOptionRepository.findByOpOg_OgIdIn(ogIdList);
        Map<Long, List<MenuOption>> optionsByGroupId = menuOptionList.stream()
                .collect(Collectors.groupingBy(op -> op.getOpOg().getOgId()));

        return menuOptionGroupList.map(menuOptionGroup -> MenuOptionGroupDto.builder()
                        .ogId(menuOptionGroup.getOgId())
                        .ogName(menuOptionGroup.getOgName())
                        .ogMinSelect(menuOptionGroup.getOgMinSelect())
                        .ogMaxSelect(menuOptionGroup.getOgMaxSelect())
                        .menuOptionDtoList(
                                optionsByGroupId.getOrDefault(menuOptionGroup.getOgId(), List.of())
                                        .stream()
                                        .map(menuOption -> MenuOptionDto
                                                .builder()
                                                .opId(menuOption.getOpId())
                                                .opName(menuOption.getOpName())
                                                .opPrice(menuOption.getOpPrice())
                                                .build())
                                        .toList()
                        )
                        .build());
    }

    @Override
    public MenuOptionGroupDto selectMenuOptionGroupDetail(Long ogId) {

        MenuOptionGroup menuOptionGroup = menuOptionGroupRepository.findById(ogId).orElseThrow(() ->
                new ApiCustomException(ErrorCode.NOT_FOUND_MENU_OPTION_GROUP));

        List<MenuOption> menuOptionList = menuOptionRepository.findByOpOg_OgIdIn(List.of(menuOptionGroup.getOgId()));

        return MenuOptionGroupDto.builder()
                .ogId(menuOptionGroup.getOgId())
                .ogName(menuOptionGroup.getOgName())
                .ogMinSelect(menuOptionGroup.getOgMinSelect())
                .ogMaxSelect(menuOptionGroup.getOgMaxSelect())
                .menuOptionDtoList(menuOptionList.stream()
                        .map(menuOption -> MenuOptionDto
                                .builder()
                                .opId(menuOption.getOpId())
                                .opName(menuOption.getOpName())
                                .opPrice(menuOption.getOpPrice())
                                .build()).toList())
                .build();
    }

    @Override
    @Transactional
    public void modifyMenuOptionGroup(MenuOptionGroupDto requestDto) {

        MenuOptionGroup menuOptionGroup = menuOptionGroupRepository.findById(requestDto.getOgId()).orElseThrow(() ->
                new ApiCustomException(ErrorCode.NOT_FOUND_MENU_OPTION_GROUP));

        for (MenuOptionDto menuOptionDto : requestDto.getMenuOptionDtoList()) {
            if (menuOptionDto.getOpId() == null) {
                // 새로운 옵션은 추가
                menuOptionRepository.save(MenuOption.builder()
                        .opName(menuOptionDto.getOpName())
                        .opPrice(menuOptionDto.getOpPrice())
                        .opOg(menuOptionGroup)
                        .build());
            } else {
                // 기존 옵션은 수정
                MenuOption menuOption = menuOptionRepository.findById(menuOptionDto.getOpId()).orElseThrow(() ->
                        new ApiCustomException(ErrorCode.NOT_FOUND_MENU_OPTION));

                menuOption.modify(menuOptionDto);
            }
        }

        // 삭제 옵션 처리
        menuOptionRepository.deleteAllById(requestDto.getDeleteOpIds());

        // 메뉴옵션 그룹 수정
        menuOptionGroup.modify(requestDto);
    }

    @Override
    @Transactional
    public void deleteMenuOptionGroup(MenuOptionGroupDto requestDto) {

        menuOptionGroupJunctionRepository.deleteByMjOg_OgId(requestDto.getOgId());
        menuOptionRepository.deleteByOpOg_OgId(requestDto.getOgId());
        menuOptionGroupRepository.deleteById(requestDto.getOgId());
    }
}
