package com.sideProject.qrOrder.dto.menuSoldOut;

import com.sideProject.qrOrder.entity.Common.ENUM.MenuStatus;
import com.sideProject.qrOrder.entity.MenuSoldOut;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuSoldOutDto {

    private Long msId;
    private LocalDateTime msStart;
    private LocalDateTime msEnd;
    private Long msMeId;
    private String meName;
    private Long meCaId;
    private MenuStatus meStatus;
    private String searchWord;

    @Builder
    public MenuSoldOutDto(MenuSoldOut menuSoldOut,
                          Long msMeId,
                          String meName,
                          Long meCaId,
                          MenuStatus meStatus,
                          String searchWord) {

        if (menuSoldOut != null){
            this.msId = menuSoldOut.getMsId();
            this.msStart = menuSoldOut.getMsStart();
            this.msEnd = menuSoldOut.getMsEnd();
        }
        this.msMeId = msMeId;
        this.meName = meName;
        this.meCaId = meCaId;
        this.meStatus = meStatus;
        this.searchWord = searchWord;
    }
}
