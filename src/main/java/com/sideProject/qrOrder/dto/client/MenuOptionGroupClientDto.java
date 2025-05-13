package com.sideProject.qrOrder.dto.client;

import com.sideProject.qrOrder.dto.menuOptionGroup.MenuOptionGroupDto;
import com.sideProject.qrOrder.entity.MenuOptionGroup;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionGroupClientDto {

    private Long ogId;
    private String ogName;
    private int ogMinSelect;
    private int ogMaxSelect;
    private List<MenuOptionClientDto> menuOptionClientDtoList;

    @Builder
    public MenuOptionGroupClientDto(Long ogId,
                                    String ogName,
                                    int ogMinSelect,
                                    int ogMaxSelect,
                                    List<MenuOptionClientDto> menuOptionClientDtoList) {
        this.ogId = ogId;
        this.ogName = ogName;
        this.ogMinSelect = ogMinSelect;
        this.ogMaxSelect = ogMaxSelect;
        this.menuOptionClientDtoList = menuOptionClientDtoList == null
                ? List.of()
                : List.copyOf(menuOptionClientDtoList);
    }
}
