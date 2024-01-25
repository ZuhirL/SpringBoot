package com.example.springboot.error.exception;

import lombok.Getter;

@Getter
public class CdrNotFoundException extends RuntimeException {

  public static final String CDR_NOT_FOUND_DESCRIPTION = "Cdr not found";

}
