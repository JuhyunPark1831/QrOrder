package com.sideProject.qrOrder.dto.menuOptionGroup;

import com.sideProject.qrOrder.dto.menuOption.MenuOptionDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionGroupDto {

    private Long ogId;
    private String ogName;
    private int ogMinSelect;
    private int ogMaxSelect;
    private List<MenuOptionDto> menuOptionDtoList;
    private String searchWord;
    private List<Long> deleteOpIds;

    @Builder
    public MenuOptionGroupDto(Long ogId,
                              String ogName,
                              int ogMinSelect,
                              int ogMaxSelect,
                              List<MenuOptionDto> menuOptionDtoList,
                              String searchWord) {
        this.ogId = ogId;
        this.ogName = ogName;
        this.ogMinSelect = ogMinSelect;
        this.ogMaxSelect = ogMaxSelect;
        this.menuOptionDtoList = menuOptionDtoList;
        this.searchWord = searchWord;
    }
}
