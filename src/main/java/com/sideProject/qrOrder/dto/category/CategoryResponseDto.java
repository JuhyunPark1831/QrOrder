package com.sideProject.qrOrder.dto.category;

import com.sideProject.qrOrder.entity.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryResponseDto {

    private Long caId;
    private String caName;
    private int caSeq;
    private int caMenuCount;

    @Builder
    public CategoryResponseDto(Long caId,
                               String caName,
                               int caSeq,
                               int caMenuCount) {
        this.caId = caId;
        this.caName = caName;
        this.caSeq = caSeq;
        this.caMenuCount = caMenuCount;
    }

    public static CategoryResponseDto from(Category category) {
        return CategoryResponseDto.builder()
                .caId(category.getCaId())
                .caName(category.getCaName())
                .caSeq(category.getCaSeq())
                .build();
    }
}
