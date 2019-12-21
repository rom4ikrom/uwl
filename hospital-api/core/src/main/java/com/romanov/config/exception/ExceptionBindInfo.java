package com.romanov.config.exception;

import lombok.Data;

@Data
public class ExceptionBindInfo
{
    private ExceptionCode code;
    private String message;

    public ExceptionBindInfo (ExceptionCode code, String message)
    {
        this.code = code;
        this.message = message;
    }
}
