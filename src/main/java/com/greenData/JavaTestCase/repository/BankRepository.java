package com.greenData.JavaTestCase.repository;

import com.greenData.JavaTestCase.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{
    List<Bank> findByName(String name);
    List<Bank> findByBic(String bic);
}
