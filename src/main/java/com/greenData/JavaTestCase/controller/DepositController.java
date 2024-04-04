package com.greenData.JavaTestCase.controller;

import com.greenData.JavaTestCase.dto.DepositDTO;
import com.greenData.JavaTestCase.exception.ClientWithNameDoesntExistException;
import com.greenData.JavaTestCase.model.Deposit;
import com.greenData.JavaTestCase.service.DepositService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/deposits")
@AllArgsConstructor
public class DepositController {
    private final DepositService depositService;

    @GetMapping
    public ResponseEntity<List<Deposit>> getAllDeposits(){
        return new ResponseEntity<>(depositService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/byBank")
    public ResponseEntity<List<Deposit>> getBankByBank(@RequestParam("bank") Long id){
        try {
            List<Deposit> deposits = depositService.readByBank(id);
            return ResponseEntity.ok(deposits);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("/byClient")
    public ResponseEntity<List<Deposit>> getBankByClient(@RequestParam("client") Long id){
        try {
            List<Deposit> deposits = depositService.readByClient(id);
            return ResponseEntity.ok(deposits);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deposit> getClientByID(@PathVariable(value = "id") Long id){
        Deposit deposit = null;
        try {
            deposit = depositService.readById(id);
            return ResponseEntity.ok(deposit);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deposit);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Deposit> addDeposit(@RequestBody DepositDTO deposit) throws ClientWithNameDoesntExistException {
        return new ResponseEntity<>(depositService.create(deposit), HttpStatus.CREATED);
    }

    @PutMapping( "/update" )
    public ResponseEntity<Deposit> updateDeposit(@RequestBody Deposit deposit) {
        return new ResponseEntity<>(depositService.update(deposit), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Deposit> delete(@PathVariable(value = "id") Long id) {
        try {
            depositService.delete(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
