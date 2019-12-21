package com.romanov.config.exception;

public class BaseException extends Exception {

    private ExceptionCode code;

    public BaseException(ExceptionCode code, String message)
    {
        super(message);
        this.code = code;
    }

    public BaseException(ExceptionCode code, String message, Throwable cause)
    {
        super(message, cause);
        this.code = code;
    }

    public ExceptionCode getExceptionCode()
    {
        return code;
    }

}
