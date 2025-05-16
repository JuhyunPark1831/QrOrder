package com.sideProject.qrOrder.dto.client;

import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionClientDto {

    private Long opId;
    private String opName;
    private int opPrice;
    private SoldOutStatus opStatus;

    @Builder
    public MenuOptionClientDto(Long opId,
                               String opName,
                               int opPrice,
                               SoldOutStatus opStatus) {
        this.opId = opId;
        this.opName = opName;
        this.opPrice = opPrice;
        this.opStatus = opStatus;
    }
}
