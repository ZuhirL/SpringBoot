package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
class GetByIdTest {

    @Autowired
    private MockMvc mockMvc;
/*
    @Test
    void getByIdSuccess() throws Exception {
        mockMvc.perform(get("/cdr/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"sessionIdentification\":\"CDR001\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684483200.000000000,\"endAt\":1684490400.000000000,\"amount\":25.50}"));
    }
   */

    @Test
    void getByIdFail() throws Exception {
        mockMvc.perform(get("/cdr/id/100"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"errorCode\":\"CDR-001\",\"errorDescription\":\"Cdr not found\"}"));
    }

}
