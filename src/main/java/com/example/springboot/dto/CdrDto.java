package com.example.springboot.dto;

import com.example.springboot.validator.TimeRangeValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;

@Data
@TimeRangeValidation(message = "StartAt can't be after endAt")
public class CdrDto {

  @Hidden
  private Long id;

  @NotBlank(message = "sessionIdentification is mandatory")
  private String sessionIdentification;

  @NotBlank(message = "vehicleIdentification is mandatory")
  private String vehicleIdentification;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
  @NotNull(message = "startAt is mandatory")
  private Instant startAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
  @NotNull(message = "endAt is mandatory")
  private Instant endAt;

  @Positive(message = "amount should be bigger than zero")
  @NotNull(message = "amount is mandatory")
  private BigDecimal amount;

}
