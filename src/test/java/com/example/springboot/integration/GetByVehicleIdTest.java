package com.example.springboot.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class GetByVehicleIdTest {

  @Autowired
  private MockMvc mvc;

  @Test
  void getByVehicleIdSuccess() throws Exception {
    mvc.perform(get("/cdr/vehicle-identification/VH001"))
        .andExpect(status().isOk())
        .andExpect(content().json("""
            {
              "content":
              [
                  {
                      "id": 1,
                      "sessionIdentification": "CDR001",
                      "vehicleIdentification": "VH001",
                      "startAt": "2023-05-19T10:00:00Z",
                      "endAt": "2023-05-19T12:00:00Z",
                      "amount": 25.50
                  },
                  {
                      "id": 4,
                      "sessionIdentification": "CDR004",
                      "vehicleIdentification": "VH001",
                      "startAt": "2023-05-19T08:00:00Z",
                      "endAt": "2023-05-19T09:30:00Z",
                      "amount": 20.00
                  },
                  {
                      "id": 7,
                      "sessionIdentification": "CDR007",
                      "vehicleIdentification": "VH001",
                      "startAt": "2023-05-19T11:30:00Z",
                      "endAt": "2023-05-19T12:30:00Z",
                      "amount": 15.25
                  }
              ]
            }
            """));
  }

  @Test
  void getByVehicleIdSuccessWithPage0AndSize2() throws Exception {
    mvc.perform(get("/cdr/vehicle-identification/VH001")
            .queryParam("page", "0")
            .queryParam("size", "2"))
        .andExpect(status().isOk())
        .andExpect(content().json("""
            {
              "content":
              [
                  {
                      "id": 1,
                      "sessionIdentification": "CDR001",
                      "vehicleIdentification": "VH001",
                      "startAt": "2023-05-19T10:00:00Z",
                      "endAt": "2023-05-19T12:00:00Z",
                      "amount": 25.50
                  },
                  {
                      "id": 4,
                      "sessionIdentification": "CDR004",
                      "vehicleIdentification": "VH001",
                      "startAt": "2023-05-19T08:00:00Z",
                      "endAt": "2023-05-19T09:30:00Z",
                      "amount": 20.00
                  }
              ]
            }
            """));
  }

  @Test
  void getByVehicleIdSuccessWithPage1AndSize2() throws Exception {
    mvc.perform(get("/cdr/vehicle-identification/VH001")
            .queryParam("page", "1")
            .queryParam("size", "2"))
        .andExpect(status().isOk())
        .andExpect(content().json("""
            {
              "content":
              [
                  {
                      "id": 7,
                      "sessionIdentification": "CDR007",
                      "vehicleIdentification": "VH001",
                      "startAt": "2023-05-19T11:30:00Z",
                      "endAt": "2023-05-19T12:30:00Z",
                      "amount": 15.25
                  }
              ]
            }
            """));
  }

  @Test
  void getByVehicleIdSuccessWithPage1AndSize3() throws Exception {
    mvc.perform(get("/cdr/vehicle-identification/VH001")
            .queryParam("page", "1")
            .queryParam("size", "3"))
        .andExpect(status().isOk())
        .andExpect(content().json("""
                      {
                        "content": []
                      }
            """));
  }

  @Test
  void getByVehicleIdSuccessWithPage0AndSize2AndSortStartTimeDesc() throws Exception {
    mvc.perform(get("/cdr/vehicle-identification/VH001")
            .queryParam("page", "0")
            .queryParam("size", "2")
            .queryParam("sort", "startTime")
            .queryParam("order", "desc"))
        .andExpect(status().isOk())
        .andExpect(content().json("""
            {
              "content":
              [
                {
                    "id": 7,
                    "sessionIdentification": "CDR007",
                    "vehicleIdentification": "VH001",
                    "startAt": "2023-05-19T11:30:00Z",
                    "endAt": "2023-05-19T12:30:00Z",
                    "amount": 15.25
                },
                {
                    "id": 1,
                    "sessionIdentification": "CDR001",
                    "vehicleIdentification": "VH001",
                    "startAt": "2023-05-19T10:00:00Z",
                    "endAt": "2023-05-19T12:00:00Z",
                    "amount": 25.50
                }
              ]
            }
            """));
  }

  @Test
  void getByVehicleIdNotExistSuccess() throws Exception {
    mvc.perform(get("/cdr/vehicle-identification/VH00100"))
        .andExpect(status().isOk())
        .andExpect(content().json("""
                      {
                        "content": []
                      }
            """));
  }

}
