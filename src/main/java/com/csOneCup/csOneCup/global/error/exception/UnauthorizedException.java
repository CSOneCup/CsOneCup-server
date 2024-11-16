package com.csOneCup.csOneCup.global.error.exception;


import com.csOneCup.csOneCup.global.error.ErrorCode;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED);
    }

    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}