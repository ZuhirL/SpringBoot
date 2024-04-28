package com.example.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Configuration
public class TestConfig {

  @Autowired
  private MockMvc mockMvc;

  @Bean
  public String jwtToken() throws Exception {
    signUp();
    return extractToken(signIn());
  }

  private void signUp() throws Exception {
    String signupRequest = """
        {
          "login": "myusername",
          "password": "123456",
          "role": "ADMIN"
        }
        """;
    mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(signupRequest))
        .andReturn();
  }

  private String signIn() throws Exception {
    String signinRequest = """
        {
          "login": "myusername",
          "password": "123456"
        }
        """;
    MvcResult signInResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(signinRequest))
        .andReturn();

    return signInResult.getResponse().getContentAsString();
  }

  private String extractToken(String result) {
    return result.substring(result.indexOf(":") + 2, result.length() - 2);
  }
}

