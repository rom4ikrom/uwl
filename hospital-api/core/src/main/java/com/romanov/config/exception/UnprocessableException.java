package com.romanov.config.exception;

public class UnprocessableException extends BaseException {

    public UnprocessableException(ExceptionCode code, String message) {
        super(code, message);
    }
}
