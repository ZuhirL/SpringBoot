package com.example.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class StatusController {

  @GetMapping(value = "/alive")
  public String alive() {
    return "OK";
  }

  @GetMapping(value = "/healthz")
  public String healthz() {
    return "OK";
  }

}
