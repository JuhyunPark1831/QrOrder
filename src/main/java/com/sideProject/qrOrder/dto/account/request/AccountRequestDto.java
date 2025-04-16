package com.sideProject.qrOrder.dto.account.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountRequestDto {

    private String acLoginId;
    private String acPassword;
    private String acName;
    private String searchWord;

    @Builder
    public AccountRequestDto(String acLoginId,
                             String acPassword,
                             String acName,
                             String searchWord) {
        this.acLoginId = acLoginId;
        this.acPassword = acPassword;
        this.acName = acName;
        this.searchWord = searchWord;
    }
}
