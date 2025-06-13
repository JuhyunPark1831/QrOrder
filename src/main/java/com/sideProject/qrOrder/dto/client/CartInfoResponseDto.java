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
    private List<MenuOptionGroupClientDto> menuOptionGroupClientDtoList;
    private int totalPrice;

    @Builder
    public CartInfoResponseDto(Long meId,
                               List<MenuOptionGroupClientDto> menuOptionGroupClientDtoList,
                               int totalPrice) {
        this.meId = meId;
        this.menuOptionGroupClientDtoList = menuOptionGroupClientDtoList;
        this.totalPrice = totalPrice;
    }
}
