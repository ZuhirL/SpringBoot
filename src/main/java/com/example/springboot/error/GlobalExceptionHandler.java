package com.example.springboot.error;

import static com.example.springboot.error.ErrorCodeEnum.CDR_NOT_FOUND;
import static com.example.springboot.error.ErrorCodeEnum.GENERIC_ERROR;
import static com.example.springboot.error.ErrorCodeEnum.INVALID_REQUEST_BODY;

import com.example.springboot.error.exception.CdrNotFoundException;
import com.example.springboot.error.exception.TimeOverlapException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorResponse handleException(Exception ex) {
    log.error("Exception", ex);
    List<String> errors = new ArrayList<>();
    errors.add(ex.getMessage());
    return new ErrorResponse(GENERIC_ERROR.getErrorCode(),
        GENERIC_ERROR.getErrorDescription(), errors.toArray(new String[0]));
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    log.warn("MethodArgumentNotValidException", ex);
    List<String> errors = new ArrayList<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
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
    log.warn("TimeOverlapException", ex);
    List<String> errors = new ArrayList<>();
    errors.add(ex.getMessage());
    return new ErrorResponse(INVALID_REQUEST_BODY.getErrorCode(),
        INVALID_REQUEST_BODY.getErrorDescription(), errors.toArray(new String[0]));
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(CdrNotFoundException.class)
  public ErrorResponse handleCdrNotFoundException(CdrNotFoundException ex) {
    log.warn("CdrNotFoundException", ex);
    return new ErrorResponse(CDR_NOT_FOUND.getErrorCode(), CDR_NOT_FOUND.getErrorDescription());
  }

}
