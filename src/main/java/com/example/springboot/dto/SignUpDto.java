package com.example.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpDto {

  @NotBlank(message = "Username is mandatory")
  private String login;
  @NotBlank(message = "Password is mandatory")
  private String password;
  @NotNull(message = "Role is mandatory")
  private UserRole role;

}

