package com.example.springboot.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtTokenFilter implements Filter {

  @Autowired
  private String jwtToken;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    httpRequest = new HttpServletRequestWrapper(httpRequest) {
      @Override
      public String getHeader(String name) {
        if ("Authorization".equals(name)) {
          return "Bearer " + jwtToken;
        }
        return super.getHeader(name);
      }
    };

    chain.doFilter(httpRequest, httpResponse);
  }

}
