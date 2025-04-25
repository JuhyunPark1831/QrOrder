package com.sideProject.qrOrder.dto.menuOption;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionDto {

    private Long opId;
    private String opName;
    private int opPrice;

    @Builder
    public MenuOptionDto(Long opId,
                         String opName,
                         int opPrice) {
        this.opId = opId;
        this.opName = opName;
        this.opPrice = opPrice;
    }
}
