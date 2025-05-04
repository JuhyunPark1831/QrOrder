package com.sideProject.qrOrder.dto.menuOptionSoldOut;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionSoldOutListDto {

    private Long ogId;
    private String ogName;
    private List<MenuOptionSoldOutDto> menuOptionSoldOutDtoList;

    @Builder
    public MenuOptionSoldOutListDto(Long ogId,
                                    String ogName,
                                    List<MenuOptionSoldOutDto> menuOptionSoldOutDtoList) {
        this.ogId = ogId;
        this.ogName = ogName;
        this.menuOptionSoldOutDtoList = menuOptionSoldOutDtoList;
    }
}
