package com.csOneCup.csOneCup.global.error.exception;

import com.csOneCup.csOneCup.global.error.ErrorCode;

public class ForbiddenException extends BusinessException {
    public ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}