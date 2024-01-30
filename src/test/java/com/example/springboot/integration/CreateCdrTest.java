package com.example.springboot.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
class CreateCdrTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void createCdrSuccess() throws Exception {
    mockMvc.perform(post("/cdr")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                    "sessionIdentification": "1213",
                    "vehicleIdentification": "VH001",
                    "startAt": "2024-05-19T15:10:00Z",
                    "endAt": "2024-05-19T15:10:01Z",
                    "amount": 1.2
                }
                """
            ))
        .andExpect(status().isOk());
  }

  @Test
  void createCdrFailEmptyBody() throws Exception {
    mockMvc.perform(post("/cdr")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}"))
        .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("CDR-002"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.errorDescription").value("Invalid request body"));
  }

  @Test
  void createCdrFailWronRange() throws Exception {
    mockMvc.perform(post("/cdr")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                    "sessionIdentification": "1213",
                    "vehicleIdentification": "VH001",
                    "startAt": "2023-05-18T15:10:00Z",
                    "endAt": "2024-05-19T15:10:01Z",
                    "amount": 1.2
                }
                """
            ))
        .andExpect(status().isBadRequest())
        .andExpect(content().json("""
            {
                "errorCode": "CDR-002",
                "errorDescription": "Invalid request body",
                "errorDetails": [
                    "Start time can't be before last end time"
                ]
            }
            """));
  }

}
