package com.sideproject.qrordermanager.dto.account.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtResponseDto {

    private String accessToken;
    private String refreshToken;

    @Builder
    public JwtResponseDto (String accessToken,
                          String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
