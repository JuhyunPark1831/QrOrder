package com.sideProject.qrOrder.dto.menu;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuDto {

    private String meName;
    private Long meCaId;
    private int mePrice;
    private String meDescription;
    private MultipartFile meImage;
    private List<Long> ogIdList;

    @Builder
    public MenuDto(String meName,
                   Long meCaId,
                   int mePrice,
                   String meDescription,
                   MultipartFile meImage,
                   List<Long> ogIdList) {
        this.meName = meName;
        this.meCaId = meCaId;
        this.mePrice = mePrice;
        this.meDescription = meDescription;
        this.meImage = meImage;
        this.ogIdList = ogIdList;
    }
}
