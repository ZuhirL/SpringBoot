package com.example.springboot.error.exception;

import lombok.Getter;

@Getter
public class TimeOverlapException extends RuntimeException {

  private static final String TIME_OVERLAP_DESCRIPTION = "Start time can't be before last end time";

}
