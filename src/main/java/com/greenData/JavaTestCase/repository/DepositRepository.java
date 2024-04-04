package com.greenData.JavaTestCase.repository;

import com.greenData.JavaTestCase.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findByBankId(Long bankId);
    List<Deposit> findByClientId(Long clientId);
}
