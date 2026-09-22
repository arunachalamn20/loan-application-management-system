package com.example.loanmanagement.service;

import com.example.loanmanagement.entity.Loan_application;
import com.example.loanmanagement.entity.Repayment;
import com.example.loanmanagement.repository.loan_application_repository;
import com.example.loanmanagement.repository.repayment_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class repayment_service {

    @Autowired
    repayment_repository repayment_rep;

    @Autowired
    loan_application_repository loan_rep;

    //create a repayment
    public Repayment create(Long loanid,Repayment repayment){

        Loan_application loan = loan_rep.findById(loanid).orElse(null);
        if(loan==null){
            return null;
        }
        repayment.setPaymentdate(LocalDateTime.now());
        repayment.setLoan(loan);
        return repayment_rep.save(repayment);
    }

    //get repayment by repayment id
    public Repayment getbyid(Long id){
        return repayment_rep.findById(id).orElse(null);
    }

    //get all repayments
    public List<Repayment> getall(){
        return repayment_rep.findAll();
    }

    //update a repayment by repayment id
    public Repayment update(Long id,Repayment repayment){
        Repayment existing_repayment = repayment_rep.findById(id).orElse(null);
        if(existing_repayment!=null){
            existing_repayment.setPayment_amount(repayment.getPayment_amount());
            return repayment_rep.save(existing_repayment);
        }
        return null;
    }

    //delete repayment by repayment id
    public void deletebyid(Long id){
        repayment_rep.deleteById(id);
    }

    //delete all repayments
    public void deleteall(){
        repayment_rep.deleteAll();
    }

    //get repayments by using loan id
    public List<Repayment> getbyloanid(Long loanid){
        return repayment_rep.findByLoanId(loanid);
        // 1. findByLoanId(101) is called from the Repayment entity.
        // 2. JPA looks for loanId column in repayment entity.
        // 3. There is no direct loanId field in the Repayment entity.
        // 4. Repayment has a loan reference because of @ManyToOne, then JPA looks into that loan reference.
        // 5. That loan reference points to the Loan entity.
        // 6. The Loan entity has an id field.
        // 7. So JPA understands it as loan.id, that is take id field from Loan table.
        // 8. There is a relationship between Repayment and Loan.
        // 9. The Repayment table has the loan's foreign key.
        // 10. The Loan table has the loan's primary key.
        // 11. JPA/Hibernate uses this relationship between the foreign key and primary key and makes a inner join between them, after joining the resulting table has all columns from both repayment and loan table.
        // 12. It checks the id in the Loan table.
        // 13. It finds the Loan whose id matches 101.
        // 14. It gives the Repayment record(s) associated with that Loan.
    }

    //get the total payment paid for a loan
    public Double gettotalpaid(Long loanid){
        Loan_application l = loan_rep.findById(loanid).orElse(null);
        if(l==null){
            return null;
        }
        List<Repayment> repayments = repayment_rep.findByLoanId(loanid);
        Double total = 0.0;

        for(Repayment repayment : repayments){
            total = total+repayment.getPayment_amount();
        }
        return total;
    }

    //get the remaining amount to be paid for a loan
    public Double getremainingamount(Long loanid){

        Loan_application loan = loan_rep.findById(loanid).orElse(null);
        if(loan==null){
            return null;
        }

        Double totalpaid = gettotalpaid(loanid);
        return loan.getLoanamount() - totalpaid;

    }
}
