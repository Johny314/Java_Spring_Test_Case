package com.greenData.JavaTestCase.service;

import com.greenData.JavaTestCase.dto.DepositDTO;
import com.greenData.JavaTestCase.exception.ClientWithNameDoesntExistException;
import com.greenData.JavaTestCase.model.Deposit;
import com.greenData.JavaTestCase.repository.DepositRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepositService {

    private final DepositRepository depositRepository;
    private final BankService bankService;
    private final ClientService clientService;
    public List<Deposit> readAll(){
        return depositRepository.findAll();
    }

    public Deposit readById(Long id){
        return depositRepository.findById(id).orElseThrow(() -> new RuntimeException("Deposit not found - " + id ));
    }

    public List<Deposit> readByBank(Long id){
        return depositRepository.findByBankId(id);
    }

    public List<Deposit> readByClient(Long id){
        return depositRepository.findByClientId(id);
    }

    public Deposit create(DepositDTO dto) throws ClientWithNameDoesntExistException {
        Deposit deposit = Deposit.builder()
                .date(dto.getDate())
                .percent(dto.getPercent())
                .bank(bankService.readById(dto.getBank_id()))
                .client(clientService.readById(dto.getClient_id()))
                .month_num(dto.getMonth_num())
                .build();
        return depositRepository.save(deposit);
    }

    public Deposit update(Deposit deposit){
        return depositRepository.save(deposit);
    }

    public void delete(Long id){
        depositRepository.deleteById(id);
    }
}
