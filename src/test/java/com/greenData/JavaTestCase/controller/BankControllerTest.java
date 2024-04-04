package com.greenData.JavaTestCase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenData.JavaTestCase.model.Bank;
import com.greenData.JavaTestCase.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BankControllerTest {
    @Mock
    private BankService bankService;

    @InjectMocks
    private BankController bankController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(bankController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllBanks() throws Exception {
        mockMvc.perform(get("/banks"))
                .andExpect(status().isOk());
    }

    @Test
    void addBank() throws Exception {
        Bank bank = new Bank(5L,"SBER","11111");
        String bankJson = objectMapper.writeValueAsString(bank);
        mockMvc.perform(post("/banks/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bankJson))
                .andExpect(status().isCreated());
    }

    @Test
    void getBankById() throws Exception {
        Bank bank = new Bank(1L,"SBER","11111");
        when(bankService.readById(1L)).thenReturn(bank);
        mockMvc.perform(get("/banks/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("SBER"))
                .andExpect(jsonPath("$.bic").value("11111"));
        verify(bankService, times(1)).readById(1L);
    }

    @Test
    void getBankByName() throws Exception {
        mockMvc.perform(get("/banks/by?name={name}", "SBER"))
                .andExpect(status().isOk());

    }

    @Test
    void getBankByBic() throws Exception {
        mockMvc.perform(get("/banks/byBic?bic={int}", 111))
                .andExpect(status().isOk());

    }

    @Test
    void deleteBank() throws Exception {
        mockMvc.perform(delete("/banks/delete/{id}", 1))
                .andExpect(status().isOk());
    }
}