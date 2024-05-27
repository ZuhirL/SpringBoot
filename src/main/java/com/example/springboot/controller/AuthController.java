package com.example.springboot.controller;

import com.example.springboot.config.TokenProvider;
import com.example.springboot.dao.User;
import com.example.springboot.dto.JwtDto;
import com.example.springboot.dto.SignInDto;
import com.example.springboot.dto.SignUpDto;
import com.example.springboot.service.AuthService;
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

  @PostMapping("/signup")
  public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpDto data) {
    service.signUp(data);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/signin")
  public ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto data) throws AuthenticationException {
    service.signinCheck(data);
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
    var authUser = authenticationManager.authenticate(usernamePassword);
    var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
    return ResponseEntity.ok(new JwtDto(accessToken));
  }
}
