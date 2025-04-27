package com.sideProject.qrOrder.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionGroupJunctionDto {

    private Long mjId;
    private Long ogId;

    @Builder
    public MenuOptionGroupJunctionDto(Long mjId,
                                      Long ogId) {
        this.mjId = mjId;
        this.ogId = ogId;
    }
}
