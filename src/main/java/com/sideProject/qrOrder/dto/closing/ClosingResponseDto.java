package com.sideProject.qrOrder.dto.closing;

import com.sideProject.qrOrder.dto.account.AccountResponseDto;
import com.sideProject.qrOrder.entity.Account;
import com.sideProject.qrOrder.entity.Closing;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClosingResponseDto {

    private Long clId;
    private LocalDateTime clStart;
    private LocalDateTime clEnd;

    @Builder
    public ClosingResponseDto(Long clId,
                              LocalDateTime clStart,
                              LocalDateTime clEnd) {
        this.clId = clId;
        this.clStart = clStart;
        this.clEnd = clEnd;
    }

    public static ClosingResponseDto from(Closing closing) {
        return ClosingResponseDto.builder()
                .clId(closing.getClId())
                .clStart(closing.getClStart())
                .clEnd(closing.getClEnd())
                .build();
    }
}
