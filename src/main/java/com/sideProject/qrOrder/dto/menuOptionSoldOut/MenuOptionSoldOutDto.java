package com.sideProject.qrOrder.dto.menuOptionSoldOut;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionSoldOutDto {

    private Long osOpId;
    private String opName;
    private Long osId;
    private LocalDateTime osStart;
    private LocalDateTime osEnd;
    private String searchWord;

    @Builder
    public MenuOptionSoldOutDto(Long osId,
                                LocalDateTime osStart,
                                LocalDateTime osEnd,
                                Long osOpId,
                                String opName,
                                String searchWord) {
        this.osId = osId;
        this.osStart = osStart;
        this.osEnd = osEnd;
        this.osOpId = osOpId;
        this.opName = opName;
        this.searchWord = searchWord;
    }

    public MenuOptionSoldOutDto(Long osOpId,
                                String opName,
                                Long osId,
                                LocalDateTime osStart,
                                LocalDateTime osEnd) {
        this.osOpId = osOpId;
        this.opName = opName;
        this.osId = osId;
        this.osStart = osStart;
        this.osEnd = osEnd;
    }
}
