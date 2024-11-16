package com.csOneCup.csOneCup.global.error.exception;


import com.csOneCup.csOneCup.global.error.ErrorCode;

public class InvalidValueException extends BusinessException {
    public InvalidValueException() {
        super(ErrorCode.BAD_REQUEST);
    }

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}