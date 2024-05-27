package com.example.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInDto {

  @NotBlank(message = "Username is mandatory")
  private String username;
  @NotBlank(message = "Password is mandatory")
  private String password;

}
