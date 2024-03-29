package com.example.springboot.validator;

import com.example.springboot.dto.CdrDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeRangeValidator implements ConstraintValidator<TimeRangeValidation, CdrDto> {

  @Override
  public boolean isValid(CdrDto value, ConstraintValidatorContext context) {
    if (value.getStartAt() != null && value.getEndAt() != null) {
      return value.getStartAt().toEpochMilli() < value.getEndAt().toEpochMilli();
    }
    return true;
  }
}
