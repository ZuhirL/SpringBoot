package com.example.springboot.controller;

import com.example.springboot.dao.CdrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("status")
@RequiredArgsConstructor
public class StatusController {

  private final CdrRepository cdrRepository;

  @GetMapping(value = "/alive")
  public String alive() {
    return "OK";
  }

  @GetMapping(value = "/healthz")
  public String healthz() {
    cdrRepository.count();
    return "OK";
  }

}
