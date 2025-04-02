package com.sideproject.qrordermanager.common.error;

import lombok.Getter;

@Getter
public class ViewCustomException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String redirectURL;

    public ViewCustomException(ErrorCode errorCode,
                               String redirectURL) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.redirectURL = redirectURL;
    }
}
