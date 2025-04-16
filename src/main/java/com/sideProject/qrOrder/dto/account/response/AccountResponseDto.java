package com.sideProject.qrOrder.dto.account.response;

import com.sideProject.qrOrder.entity.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountResponseDto {

    private Long acId;
    private String acName;
    private String acLoginId;
    private LocalDateTime createdAt;

    @Builder
    public AccountResponseDto(Long acId,
                              String acName,
                              String acLoginId,
                              LocalDateTime createdAt) {
        this.acId = acId;
        this.acName = acName;
        this.acLoginId = acLoginId;
        this.createdAt = createdAt;
    }

    public static AccountResponseDto from(Account account) {
        return AccountResponseDto.builder()
                .acId(account.getAcId())
                .acName(account.getAcName())
                .acLoginId(account.getAcLoginId())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
