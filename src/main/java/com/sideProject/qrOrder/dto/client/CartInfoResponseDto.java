package com.sideProject.qrOrder.dto.client;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartInfoResponseDto {

    private Long meId;
    private String meName;
    private int mePrice;
    private List<MenuOptionGroupClientDto> menuOptionGroupClientDtoList;
    private int totalPrice;

    @Builder
    public CartInfoResponseDto(Long meId,
                               String meName,
                               int mePrice,
                               List<MenuOptionGroupClientDto> menuOptionGroupClientDtoList,
                               int totalPrice) {
        this.meId = meId;
        this.meName = meName;
        this.mePrice = mePrice;
        this.menuOptionGroupClientDtoList = menuOptionGroupClientDtoList;
        this.totalPrice = totalPrice;
    }
}
