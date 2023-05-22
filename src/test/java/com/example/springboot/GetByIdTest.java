package com.example.springboot;

import com.example.springboot.controller.CdrController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest
class GetByIdTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getByIdSuccess() throws Exception {
        mockMvc.perform(get("/cdr/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"sessionIdentification\":\"CDR001\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684483200.000000000,\"endAt\":1684490400.000000000,\"amount\":25.50}"));
    }

    @Test
    void getByIdFail() throws Exception {
        mockMvc.perform(get("/cdr/id/100"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"errorCode\":\"CDR-001\",\"errorDescription\":\"Cdr not found\"}"));
    }

}
