package com.greenData.JavaTestCase.controller;

import com.greenData.JavaTestCase.model.Bank;
import com.greenData.JavaTestCase.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/banks")
@AllArgsConstructor
public class BankController {
    private final BankService bankService;

    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks(){
        return new ResponseEntity<>(bankService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/by")
    public ResponseEntity<List<Bank>> getBankByName(@RequestParam("name") String name){
        try {
            List<Bank> banks = bankService.readByName(name);
            return ResponseEntity.ok(banks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("/byBic")
    public ResponseEntity<List<Bank>> getBankByBic(@RequestParam("bic") String bic){
        try {
            List<Bank> banks = bankService.readByBic(bic);
            return ResponseEntity.ok(banks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getClientByID(@PathVariable(value = "id") Long id){
        Bank bank = null;
        try {
            bank = bankService.readById(id);
            return ResponseEntity.ok(bank);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bank);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Bank> addBank(@RequestBody Bank bank){
        return new ResponseEntity<>(bankService.create(bank), HttpStatus.CREATED);
    }

    @PutMapping( "/update" )
    public ResponseEntity<Bank> updateClient(@RequestBody Bank bank) {
        return new ResponseEntity<>(bankService.update(bank), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Bank> delete(@PathVariable(value = "id") Long id) {
        try {
            bankService.delete(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
