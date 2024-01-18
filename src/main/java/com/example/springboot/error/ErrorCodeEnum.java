package com.example.springboot.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

  GENERIC_ERROR("CDR-001", "Generic error"),
  INVALID_REQUEST_BODY("CDR-002", "Invalid request body"),
  CDR_NOT_FOUND("CDR-001", "Cdr not found");

  private final String errorCode;
  private final String errorDescription;

}