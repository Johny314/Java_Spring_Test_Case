package com.greenData.JavaTestCase.controller;

import com.greenData.JavaTestCase.exception.ClientWithNameDoesntExistException;
import com.greenData.JavaTestCase.model.Client;
import com.greenData.JavaTestCase.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        return new ResponseEntity<>(clientService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/by")
    public ResponseEntity<List<Client>> getClientsByName(@RequestParam("name") String name){
        try {
            List<Client> clients = clientService.readByName(name);
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientByID(@PathVariable(value = "id") Long id) throws ClientWithNameDoesntExistException {
        Client client = null;
        try {
            client = clientService.readById(id);
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(client);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        return new ResponseEntity<>(clientService.create(client), HttpStatus.CREATED);
    }

    @PutMapping( "/update" )
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.updateClient(client), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Client> delete(@PathVariable(value = "id") Long id) {
        try {
            clientService.delete(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
