package com.example.springboot.dto;

import com.example.springboot.validator.TimeRangeValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@TimeRangeValidation(message = "StartAt can't be after endAt")
public class CdrDto {

    private Long id;

    @NotBlank(message = "sessionIdentification is mandatory")
    private String sessionIdentification;

    @NotBlank(message = "vehicleIdentification is mandatory")
    private String vehicleIdentification;

    @NotNull(message = "startAt is mandatory")
    private Instant startAt;

    @NotNull(message = "endAt is mandatory")
    private Instant endAt;

    @Positive(message = "amount should be bigger than zero")
    @NotNull(message = "amount is mandatory")
    private BigDecimal amount;

}