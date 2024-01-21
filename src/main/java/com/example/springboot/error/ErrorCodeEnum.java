package com.example.springboot.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

  GENERIC_ERROR("CDR-000", "Generic error"),
  CDR_NOT_FOUND("CDR-001", "Cdr not found"),
  INVALID_REQUEST_BODY("CDR-002", "Invalid request body");

  private final String errorCode;
  private final String errorDescription;

}
