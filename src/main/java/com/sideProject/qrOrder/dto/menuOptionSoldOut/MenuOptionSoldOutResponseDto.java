package com.sideProject.qrOrder.dto.menuOptionSoldOut;

import com.sideProject.qrOrder.dto.menuOption.MenuOptionDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionSoldOutResponseDto {

    private Long ogId;
    private String ogName;
    private List<MenuOptionSoldOutDto> menuOptionSoldOutDtoList;

    @Builder
    public MenuOptionSoldOutResponseDto(Long ogId,
                                        String ogName,
                                        List<MenuOptionSoldOutDto> menuOptionSoldOutDtoList) {
        this.ogId = ogId;
        this.ogName = ogName;
        this.menuOptionSoldOutDtoList = menuOptionSoldOutDtoList;
    }
}
