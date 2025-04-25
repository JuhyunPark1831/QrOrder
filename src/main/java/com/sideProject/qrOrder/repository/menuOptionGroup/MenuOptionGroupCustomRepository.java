package com.sideProject.qrOrder.repository.menuOptionGroup;

import com.sideProject.qrOrder.dto.menuOptionGroup.MenuOptionGroupDto;
import com.sideProject.qrOrder.entity.MenuOptionGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuOptionGroupCustomRepository {

    public Page<MenuOptionGroup> findMenuOptionGroupList(Pageable pageable, MenuOptionGroupDto requestDto);
}
