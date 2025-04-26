package com.sideProject.qrOrder.dto.menu;

import com.sideProject.qrOrder.entity.Common.ENUM.MenuStatus;
import com.sideProject.qrOrder.entity.Menu;
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
    private String meImagePath;
    private List<Long> ogIdList;
    private MenuStatus meStatus;
    private String searchWord;

    @Builder
    public MenuDto(String meName,
                   Long meCaId,
                   int mePrice,
                   String meDescription,
                   MultipartFile meImage,
                   String meImagePath,
                   List<Long> ogIdList,
                   MenuStatus meStatus,
                   String searchWord) {
        this.meName = meName;
        this.meCaId = meCaId;
        this.mePrice = mePrice;
        this.meDescription = meDescription;
        this.meImage = meImage;
        this.meImagePath = meImagePath;
        this.ogIdList = ogIdList;
        this.meStatus = meStatus;
        this.searchWord = searchWord;
    }

    public static MenuDto from(Menu menu) {
        return MenuDto.builder()
                .meName(menu.getMeName())
                .meCaId(menu.getMeCa().getCaId())
                .mePrice(menu.getMePrice())
                .meDescription(menu.getMeDescription())
                .meImagePath(menu.getMeImagePath())
                .meStatus(menu.getMeStatus())
                .build();
    }
}
