package com.greenData.JavaTestCase.service;

import com.greenData.JavaTestCase.model.Bank;
import com.greenData.JavaTestCase.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class BankService {
    private final BankRepository bankRepository;

    public List<Bank> readAll(){
        return bankRepository.findAll();
    }

    public Bank readById(Long id){
        return bankRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank not found - " + id ));
    }

    public List<Bank> readByBic(String bic){
        return bankRepository.findByBic(bic);
    }

    public List<Bank> readByName(String name){
        return bankRepository.findByName(name);
    }

    public Bank create(Bank bank){
        return bankRepository.save(bank);
    }

    public Bank update(Bank bank){
        return bankRepository.save(bank);
    }

    public void delete(Long id){
        bankRepository.deleteById(id);
    }
}
