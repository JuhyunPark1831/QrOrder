package com.sideproject.qrordermanager.dto.account.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequestDto {

    private String acLoginId;
    private String acPassword;

    @Builder
    public LoginRequestDto (String acLoginId,
                            String acPassword) {
        this.acLoginId = acLoginId;
        this.acPassword = acPassword;
    }
}
