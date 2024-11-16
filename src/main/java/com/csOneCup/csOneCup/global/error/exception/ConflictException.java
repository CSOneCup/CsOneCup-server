package com.csOneCup.csOneCup.global.error.exception;


import com.csOneCup.csOneCup.global.error.ErrorCode;

public class ConflictException extends BusinessException {
    public ConflictException() {
        super(ErrorCode.CONFLICT);
    }

    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}