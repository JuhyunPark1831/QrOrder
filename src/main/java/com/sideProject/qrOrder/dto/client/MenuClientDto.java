package com.sideProject.qrOrder.dto.client;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuClientDto {

    private Long meId;
    private String meName;
    private int mePrice;
    private String meDescription;
    private String meImagePath;
    private List<MenuOptionGroupClientDto> optionGroupClientDtoList;

    @Builder
    public MenuClientDto(Long meId,
                         String meName,
                         int mePrice,
                         String meDescription,
                         String meImagePath,
                         List<MenuOptionGroupClientDto> optionGroupClientDtoList) {
        this.meId = meId;
        this.meName = meName;
        this.mePrice = mePrice;
        this.meDescription = meDescription;
        this.meImagePath = meImagePath;
        this.optionGroupClientDtoList = optionGroupClientDtoList == null
                ? List.of()
                : List.copyOf(optionGroupClientDtoList);
    }
}
