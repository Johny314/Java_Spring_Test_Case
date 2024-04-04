package com.greenData.JavaTestCase.repository;

import com.greenData.JavaTestCase.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    List<Client>  findByName(String name);
}
