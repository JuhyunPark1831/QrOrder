package com.sideProject.qrOrder.dto.closing;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClosingRequestDto {

    private LocalDateTime clStart;
    private LocalDateTime clEnd;
    private LocalDate clStartSearch;
    private LocalDate clEndSearch;

    @Builder
    public ClosingRequestDto(LocalDateTime clStart,
                             LocalDateTime clEnd,
                             LocalDate clStartSearch,
                             LocalDate clEndSearch) {
        this.clStart = clStart;
        this.clEnd = clEnd;
        this.clStartSearch = clStartSearch;
        this.clEndSearch = clEndSearch;
    }
}
