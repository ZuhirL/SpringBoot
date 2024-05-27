package com.example.springboot.controller;

import com.example.springboot.config.TokenProvider;
import com.example.springboot.dao.User;
import com.example.springboot.dto.CdrDto;
import com.example.springboot.dto.JwtDto;
import com.example.springboot.dto.SignInDto;
import com.example.springboot.dto.SignUpDto;
import com.example.springboot.error.ErrorResponse;
import com.example.springboot.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final AuthService service;
  private final TokenProvider tokenService;

  @Operation(summary = "SignUp user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User created"),
      @ApiResponse(responseCode = "400", description = "Invalid data",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))})})
  @PostMapping("/signup")
  public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpDto data) {
    service.signUp(data);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Operation(summary = "SignIn user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Login success",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = JwtDto.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid data",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class))})})
  @PostMapping("/signin")
  public ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto data) throws AuthenticationException {
    service.signinCheck(data);
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
    var authUser = authenticationManager.authenticate(usernamePassword);
    var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
    return ResponseEntity.ok(new JwtDto(accessToken));
  }
}
