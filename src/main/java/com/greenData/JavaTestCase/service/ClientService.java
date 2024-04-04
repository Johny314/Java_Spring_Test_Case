package com.greenData.JavaTestCase.service;

import com.greenData.JavaTestCase.exception.ClientWithNameDoesntExistException;
import com.greenData.JavaTestCase.model.Client;
import com.greenData.JavaTestCase.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> readAll(){
        return clientRepository.findAll();
    }

    public Client readById(Long id) throws ClientWithNameDoesntExistException {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElseThrow(() -> new ClientWithNameDoesntExistException("Client with this name not found"));
    }

    public List<Client> readByName(String name) throws ClientWithNameDoesntExistException {
        List<Client> clients = clientRepository.findByName(name);
        if(clients.isEmpty()){
            throw new ClientWithNameDoesntExistException("Client with this name not found");
        }
        else {
            return clients;
        }
    }

    public void delete(Long id){
        clientRepository.deleteById(id);
    }

    public Client create(Client client){
        return clientRepository.save(client);
    }

    public Client updateClient(Client client){
        return clientRepository.save(client);
    }
}
