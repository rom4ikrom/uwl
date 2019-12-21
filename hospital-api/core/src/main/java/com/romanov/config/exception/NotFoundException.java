package com.romanov.config.exception;

public class NotFoundException extends BaseException {

    public NotFoundException(ExceptionCode code, String message)
    {
        super(code, message);
    }

}
