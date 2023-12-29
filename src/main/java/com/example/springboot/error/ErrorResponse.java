package com.example.springboot.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

  private String errorCode;
  private String errorDescription;
  private String[] errorDetails;

  public ErrorResponse(String errorCode, String errorDescription) {
    this.errorCode = errorCode;
    this.errorDescription = errorDescription;
  }
}
