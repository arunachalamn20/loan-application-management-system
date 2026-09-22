package com.example.loanmanagement.repository;


import com.example.loanmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface customer_repository extends JpaRepository<Customer,Long>{

    List<Customer> findByName(String name);
    Customer findByEmail(String email);

}
