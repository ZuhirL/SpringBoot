package com.example.springboot.error.exception;

import lombok.Getter;

@Getter
public class CdrNotFoundException extends RuntimeException {

  public CdrNotFoundException(Long id) {
    super(String.format("Cdr not found with id %d", id));
  }

}
