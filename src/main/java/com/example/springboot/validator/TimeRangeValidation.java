package com.example.springboot.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeRangeValidator.class)
public @interface TimeRangeValidation {

  String message() default "{com.example.springboot.dto.CdrDto}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}