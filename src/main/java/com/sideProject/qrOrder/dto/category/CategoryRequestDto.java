package com.sideProject.qrOrder.dto.category;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryRequestDto {

    private Long caId;
    private String caName;
    private int caSeq;
    private String searchWord;

    @Builder
    public CategoryRequestDto(Long caId,
                              String caName,
                              int caSeq,
                              String searchWord) {
        this.caId = caId;
        this.caName = caName;
        this.caSeq = caSeq;
        this.searchWord = searchWord;
    }
}
