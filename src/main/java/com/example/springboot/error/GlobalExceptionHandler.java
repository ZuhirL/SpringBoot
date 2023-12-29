package com.example.springboot.error;

import com.example.springboot.error.exception.CdrNotFoundException;
import com.example.springboot.error.exception.TimeOverlapException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import static com.example.springboot.error.ErrorCode.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return new ErrorResponse(GENERIC_ERROR.getErrorCode(),
                GENERIC_ERROR.getErrorDescription(), errors.toArray(new String[0]));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return new ErrorResponse(INVALID_REQUEST_BODY.getErrorCode(),
                INVALID_REQUEST_BODY.getErrorDescription(), errors.toArray(new String[0]));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TimeOverlapException.class)
    public ErrorResponse handleTimeOverlapException(TimeOverlapException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getDescription());
        return new ErrorResponse(INVALID_REQUEST_BODY.getErrorCode(),
                INVALID_REQUEST_BODY.getErrorDescription(), errors.toArray(new String[0]));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CdrNotFoundException.class)
    public ErrorResponse handleCdrNotFoundException(CdrNotFoundException ex) {
        return new ErrorResponse(CDR_NOT_FOUND.getErrorCode(), CDR_NOT_FOUND.getErrorDescription());
    }

}
