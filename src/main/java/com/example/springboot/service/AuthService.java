package com.example.springboot.service;

import com.example.springboot.dao.UserEntity;
import com.example.springboot.dao.UserRepository;
import com.example.springboot.dto.SignInDto;
import com.example.springboot.dto.SignUpDto;
import com.example.springboot.error.exception.InvalidJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return repository.findByUsername(username);
  }

  public UserDetails signUp(SignUpDto data) throws InvalidJwtException {
    if (repository.findByUsername(data.getUsername()) != null) {
      throw new InvalidJwtException("Username already exists");
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
    UserEntity newUserEntity = new UserEntity(data.getUsername(), encryptedPassword, data.getRole());
    return repository.save(newUserEntity);
  }

  public void signinCheck(SignInDto data) throws InvalidJwtException {
    if (repository.findByUsername(data.getUsername()) == null) {
      throw new InvalidJwtException("Username doesn't exists");
    }
  }
}
