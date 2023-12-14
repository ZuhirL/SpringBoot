package com.example.springboot.error.exception;

import lombok.Getter;

@Getter
public class CdrNotFoundException extends RuntimeException {

    private final String description = "Cdr not found";

}
