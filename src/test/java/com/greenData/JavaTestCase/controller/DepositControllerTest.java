package com.greenData.JavaTestCase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenData.JavaTestCase.service.DepositService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DepositControllerTest {
    @Mock
    private DepositService depositService;

    @InjectMocks
    private DepositController depositController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(depositController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllDeposits() throws Exception {
        mockMvc.perform(get("/deposits"))
                .andExpect(status().isOk());
    }

    @Test
    void getDepositById() throws Exception {
        mockMvc.perform(get("/deposits/{id}", 1))
                .andExpect(status().isOk());
        verify(depositService, times(1)).readById(1L);
    }

    @Test
    void getDepositByBank() throws Exception {
        mockMvc.perform(get("/deposits/byBank?bank={id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void getDepositByClient() throws Exception {
        mockMvc.perform(get("/deposits/byClient?client={id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDeposit() throws Exception {
        mockMvc.perform(delete("/deposits/delete/{id}", 1))
                .andExpect(status().isOk());
    }
}