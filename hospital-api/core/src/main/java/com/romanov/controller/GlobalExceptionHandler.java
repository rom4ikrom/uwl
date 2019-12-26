package com.romanov.controller;

import com.romanov.config.exception.*;
import com.romanov.util.Funcs;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String GLOBAL_LOG_HEADER = "global {}";
    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ExceptionMessage handleBadArgumentException(HttpServletRequest request, MethodArgumentNotValidException ex)
    {
        BindingResult result = ex.getBindingResult();
        List<ObjectError> fieldErrors = result.getAllErrors();

        List<ExceptionBindInfo> exceptionCodes = fieldErrors.stream()
                .map( exp -> handleFieldError (exp) )
                .collect(Collectors.toList());

        log.info ("exception codes {}", exceptionCodes);

        ExceptionBindInfo ebi = exceptionCodes.get(0);

        BadParameterException badParameterException = new BadParameterException(ebi.getCode(), ebi.getMessage(), ex);

        return new ExceptionMessage(badParameterException, HttpStatus.BAD_REQUEST, request.getRequestURI(), ebi.getCode(), ebi.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadParameterException.class})
    @ResponseBody
    public ExceptionMessage handleBadParameter(HttpServletRequest req, Exception ex)
    {
        log.info(GLOBAL_LOG_HEADER, ex);
        BadParameterException bExp = (BadParameterException)ex;
        return new ExceptionMessage(ex, HttpStatus.BAD_REQUEST, req.getRequestURI(), bExp.getExceptionCode());
    }

    //handle enum deserialize exceptions
    /*
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleIncorrectData(HttpServletRequest request, HttpMessageNotReadableException ex)
    {
        String message = ex.getMostSpecificCause().getMessage();
        BadParameterException badParameterException = new BadParameterException(null, null);
        return null;
    }
    */

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ExceptionMessage handleNotFound(HttpServletRequest req, Exception ex)
    {
        log.info(GLOBAL_LOG_HEADER, ex);
        NotFoundException exp = (NotFoundException)ex;
        return new ExceptionMessage(ex, HttpStatus.NOT_FOUND, req.getRequestURI(), exp.getExceptionCode());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({UnprocessableException.class})
    @ResponseBody
    public ExceptionMessage handleUnprocessableException (HttpServletRequest req, Exception ex) {
        log.info(GLOBAL_LOG_HEADER, ex);
        UnprocessableException uExp = (UnprocessableException)ex;
        return new ExceptionMessage(uExp, HttpStatus.UNPROCESSABLE_ENTITY, req.getRequestURI(), uExp.getExceptionCode());
    }

    private ExceptionBindInfo handleFieldError(ObjectError objectError)
    {
        String message = objectError.getDefaultMessage();
        List<String> codes = Arrays.asList(objectError.getCodes());

        ExceptionCode thortfulExceptionCode =
                codes
                        .stream()
                        .map(x -> ExceptionCode.getByCode(x))
                        .filter(x -> !ExceptionCode.UNKNOWN.equals(x))
                        .findFirst().orElse(ExceptionCode.UNKNOWN);

        if(FieldError.class.equals(objectError.getClass()) && thortfulExceptionCode.equals(ExceptionCode.UNKNOWN)) {

            FieldError fieldError = (FieldError) objectError;

            if (codes.contains("NotNull"))
            {
                thortfulExceptionCode = ExceptionCode.MISSING_PARAM;
                message = Funcs.camelCaseToSnakeCase(fieldError.getField()) + " " + fieldError.getDefaultMessage();
            }
            else if (codes.contains("Size.java.lang.String"))
            {
                thortfulExceptionCode = ExceptionCode.INVALID_FIELD;
            }
            else
            {
                log.info ("field error {}", fieldError);
            }
        }

        return new ExceptionBindInfo(thortfulExceptionCode, message);
    }

}