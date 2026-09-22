package com.example.loanmanagement.repository;

import com.example.loanmanagement.entity.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface repayment_repository extends JpaRepository<Repayment,Long> {

    List<Repayment> findByLoanId(Long loanid);
    //Loan -> points to the "loan" reference in Repayment entity,
    //that is private Loan_application loan
    //this reference has all loan fields, that is loanType, loanamount, tenure, interestRate, status, applicationDate
    //Id -> points to the inner field of loan
    //that is loan.id
    //SELECT *
    //FROM repayment r
    //INNER JOIN loan_application l
    //ON r.loan_id = l.id
    //WHERE l.id = 101;
}
