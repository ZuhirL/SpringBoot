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
class GetByVehicleIdTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getByIdSuccess() throws Exception {
        mvc.perform(get("/cdr/vehicle-identification/VH001"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"sessionIdentification\":\"CDR001\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684483200.000000000,\"endAt\":1684490400.000000000,\"amount\":25.50},{\"id\":4,\"sessionIdentification\":\"CDR004\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684476000.000000000,\"endAt\":1684481400.000000000,\"amount\":20.00},{\"id\":7,\"sessionIdentification\":\"CDR007\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684488600.000000000,\"endAt\":1684492200.000000000,\"amount\":15.25}]"));
    }

    @Test
    void getByIdSuccessOrderByStartAtDesc() throws Exception {
        mvc.perform(get("/cdr/vehicle-identification/VH001?sortBy=startAt&order=desc"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":7,\"sessionIdentification\":\"CDR007\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684488600.000000000,\"endAt\":1684492200.000000000,\"amount\":15.25},{\"id\":1,\"sessionIdentification\":\"CDR001\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684483200.000000000,\"endAt\":1684490400.000000000,\"amount\":25.50},{\"id\":4,\"sessionIdentification\":\"CDR004\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684476000.000000000,\"endAt\":1684481400.000000000,\"amount\":20.00}]"));
    }

    @Test
    void getByIdSuccessOrderByStartAtAsc() throws Exception {
        mvc.perform(get("/cdr/vehicle-identification/VH001?sortBy=startAt&order=asc"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":4,\"sessionIdentification\":\"CDR004\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684476000.000000000,\"endAt\":1684481400.000000000,\"amount\":20.00},{\"id\":1,\"sessionIdentification\":\"CDR001\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684483200.000000000,\"endAt\":1684490400.000000000,\"amount\":25.50},{\"id\":7,\"sessionIdentification\":\"CDR007\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684488600.000000000,\"endAt\":1684492200.000000000,\"amount\":15.25}]"));
    }

    @Test
    void getByIdSuccessOrderByEndAtDesc() throws Exception {
        mvc.perform(get("/cdr/vehicle-identification/VH001?sortBy=endAt&order=desc"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":7,\"sessionIdentification\":\"CDR007\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684488600.000000000,\"endAt\":1684492200.000000000,\"amount\":15.25},{\"id\":1,\"sessionIdentification\":\"CDR001\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684483200.000000000,\"endAt\":1684490400.000000000,\"amount\":25.50},{\"id\":4,\"sessionIdentification\":\"CDR004\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684476000.000000000,\"endAt\":1684481400.000000000,\"amount\":20.00}]"));
    }

    @Test
    void getByIdSuccessOrderByEndAtAsc() throws Exception {
        mvc.perform(get("/cdr/vehicle-identification/VH001?sortBy=endAt&order=asc"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":4,\"sessionIdentification\":\"CDR004\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684476000.000000000,\"endAt\":1684481400.000000000,\"amount\":20.00},{\"id\":1,\"sessionIdentification\":\"CDR001\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684483200.000000000,\"endAt\":1684490400.000000000,\"amount\":25.50},{\"id\":7,\"sessionIdentification\":\"CDR007\",\"vehicleIdentification\":\"VH001\",\"startAt\":1684488600.000000000,\"endAt\":1684492200.000000000,\"amount\":15.25}]"));
    }

    @Test
    void getByIdFail() throws Exception {
        mvc.perform(get("/cdr/vehicle-identification/VH00100"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

}
