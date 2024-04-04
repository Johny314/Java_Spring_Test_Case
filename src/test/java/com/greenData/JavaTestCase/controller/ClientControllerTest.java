package com.greenData.JavaTestCase.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenData.JavaTestCase.model.Client;
import com.greenData.JavaTestCase.service.ClientService;
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
class ClientControllerTest {
    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllClients() throws Exception {
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk());

    }

    @Test
    void addClient() throws Exception {
        Client client = new Client(5L,"Ivan","Vanya","Perm","111");
        String clientJson = objectMapper.writeValueAsString(client);
        mockMvc.perform(post("/clients/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clientJson))
                .andExpect(status().isCreated());
    }

    @Test
    void getClientById() throws Exception {
        Client client = new Client(1L,"Ivan","Vanya","Perm","111");
        when(clientService.readById(1L)).thenReturn(client);
        mockMvc.perform(get("/clients/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Ivan"))
                .andExpect(jsonPath("$.short_name").value("Vanya"))
                .andExpect(jsonPath("$.address").value("Perm"))
                .andExpect(jsonPath("$.org_legal_form").value("111"));
        verify(clientService, times(1)).readById(1L);
    }

    @Test
    void getClientByName() throws Exception {
        mockMvc.perform(get("/clients/{id}", 1))
                .andExpect(status().isOk());

    }

    @Test
    void deleteClient() throws Exception {
        mockMvc.perform(delete("/clients/delete/{id}", 1))
                .andExpect(status().isOk());
    }
}
