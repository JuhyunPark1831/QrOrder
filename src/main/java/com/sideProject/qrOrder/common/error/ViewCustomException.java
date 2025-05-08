package com.sideProject.qrOrder.common.error;

import lombok.Getter;

@Getter
public class ViewCustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public ViewCustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
