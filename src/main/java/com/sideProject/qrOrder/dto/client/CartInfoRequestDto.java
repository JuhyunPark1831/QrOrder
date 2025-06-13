package com.sideProject.qrOrder.dto.client;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartInfoRequestDto {

    private Long meId;
    private List<Long> opIds;
    private int quantity;

    @Builder
    public CartInfoRequestDto(Long meId,
                              List<Long> opIds,
                              int quantity) {
        this.meId = meId;
        this.opIds = opIds;
        this.quantity = quantity;
    }
}
