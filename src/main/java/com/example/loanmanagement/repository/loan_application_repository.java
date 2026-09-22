package com.example.loanmanagement.repository;

import com.example.loanmanagement.entity.Loan_application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface loan_application_repository extends JpaRepository<Loan_application,Long> {

    List<Loan_application> findByStatus(String status);

    List<Loan_application> findByLoantype(String loantype);

    List<Loan_application> findByCustomerName(String name);
    //Customer -> points to the "customer" reference in Loan_application entity,that is private Customer customer
    //this reference has all customer fields,that is name,email,phone,address
    //Name -> points to the inner field of customer
    //that is customer.name

    //SELECT *
    //FROM loan_application l
    //INNER JOIN customer c
    //ON l.customer_id = c.id
    //WHERE c.name = 'Arun';

    List<Loan_application> findAllByOrderByApplicationdateDesc();

    Long countByStatus(String status);

    Long countByLoantype(String loantype);
}