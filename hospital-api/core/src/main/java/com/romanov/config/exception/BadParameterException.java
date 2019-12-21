package com.romanov.config.exception;

public class BadParameterException extends BaseException {

    private ExceptionCode code;

    public BadParameterException(ExceptionCode code, String message)
    {
        super(code, message);
    }

    public BadParameterException(ExceptionCode code, String message, Throwable cause)
    {
        super(code, message, cause);
    }
}
