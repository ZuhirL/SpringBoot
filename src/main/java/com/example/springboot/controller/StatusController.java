package com.example.springboot.controller;

import com.example.springboot.dao.CdrRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("status")
@RequiredArgsConstructor
@Slf4j
public class StatusController {

  private final CdrRepository cdrRepository;

  @GetMapping(value = "/alive")
  public String alive() {
    return "OK";
  }

  @GetMapping(value = "/healthz")
  public String healthz() {
    log.info("Health check endpoint called");
    cdrRepository.count();
    return "OK";
  }

}
