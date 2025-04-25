package com.sideProject.qrOrder.service.menuOptionGroup;

import com.sideProject.qrOrder.dto.menuOptionGroup.MenuOptionGroupDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuOptionGroupService {

    public void createMenuOptionGroup(MenuOptionGroupDto requestDto);
    public Page<MenuOptionGroupDto> selectMenuOptionGroup(Pageable pageable, MenuOptionGroupDto requestDto);
    public MenuOptionGroupDto selectMenuOptionGroupDetail(Long ogId);
    public void modifyMenuOptionGroup(MenuOptionGroupDto requestDto);
    public void deleteMenuOptionGroup(MenuOptionGroupDto requestDto);
}
