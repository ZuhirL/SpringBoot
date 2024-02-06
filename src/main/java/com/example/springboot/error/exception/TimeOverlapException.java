package com.example.springboot.error.exception;

import lombok.Getter;

@Getter
public class TimeOverlapException extends RuntimeException {

  public TimeOverlapException() {
    super("StartAt can't be before last endAt");
  }

}
