package com.example.springboot.error.exception;

import lombok.Getter;

@Getter
public class TimeOverlapException extends RuntimeException {

  private final String description = "Start time can't be before last end time";

}
