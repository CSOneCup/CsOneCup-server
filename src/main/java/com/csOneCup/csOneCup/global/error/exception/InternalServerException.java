package com.csOneCup.csOneCup.global.error.exception;


import com.csOneCup.csOneCup.global.error.ErrorCode;

public class InternalServerException extends BusinessException {
    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}