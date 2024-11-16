package com.csOneCup.csOneCup.global.error.exception;

import com.csOneCup.csOneCup.global.error.ErrorCode;

public class UnauthorizedTokenException extends BusinessException {
    public UnauthorizedTokenException() {
        super(ErrorCode.UNAUTHORIZED_TOKEN);
    }

    public UnauthorizedTokenException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UnauthorizedTokenException(String message) {
        super(message, ErrorCode.UNAUTHORIZED_TOKEN);
    }
}
