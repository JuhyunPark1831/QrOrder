package com.sideproject.qrordermanager.common.error;

import lombok.Getter;

@Getter
public class ApiCustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public ApiCustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
