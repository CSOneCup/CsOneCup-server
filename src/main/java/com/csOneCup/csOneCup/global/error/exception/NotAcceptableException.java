package com.csOneCup.csOneCup.global.error.exception;

import com.csOneCup.csOneCup.global.error.ErrorCode;

public class NotAcceptableException extends BusinessException{
    public NotAcceptableException() {
        super(ErrorCode.NOT_ACCEPTABLE);
    }
}
