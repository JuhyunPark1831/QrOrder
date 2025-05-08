package com.sideProject.qrOrder.dto.closing;

import com.sideProject.qrOrder.entity.Closing;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClosingDto {

    private Long clId;
    private LocalDateTime clStart;
    private LocalDateTime clEnd;
    private LocalDate clStartSearch;
    private LocalDate clEndSearch;

    @Builder
    public ClosingDto(Long clId,
                      LocalDateTime clStart,
                      LocalDateTime clEnd,
                      LocalDate clStartSearch,
                      LocalDate clEndSearch) {
        this.clId = clId;
        this.clStart = clStart;
        this.clEnd = clEnd;
        this.clStartSearch = clStartSearch;
        this.clEndSearch = clEndSearch;
    }

    public static ClosingDto from(Closing closing) {
        return ClosingDto.builder()
                .clId(closing.getClId())
                .clStart(closing.getClStart())
                .clEnd(closing.getClEnd())
                .build();
    }
}
