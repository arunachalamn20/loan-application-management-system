package com.example.loanmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Entity
public class Repayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Loan_application loan;
    //this means many repayments can point to one Loan application
    //many refers to the entity where it is created,here many points to repayment entity
    //one points to entity below the @manytoone annotation,here one points to Loan_application

    @NotNull(message = "Repayment amount is required")
    @DecimalMin(value = "1",message = "payment amount must be greater than 0")
    private Double payment_amount;

    private LocalDateTime paymentdate;

    public Repayment(){

    }

    //id - no need to write for setId(),because msql will automatically generate it,this prevents the user from sending the id
    public Long getId(){
        return id;
    }

    //loan
    public void setLoan(Loan_application loan){
        this.loan = loan;
    }
    public Loan_application getLoan(){
        return loan;
    }

    //payment_amount
    public void setPayment_amount(Double payment_amount){
        this.payment_amount = payment_amount;
    }
    public Double getPayment_amount(){
        return payment_amount;
    }

    //localdatetime
    public void setPaymentdate(LocalDateTime paymentdate){
        this.paymentdate = paymentdate;
    }
    public LocalDateTime getPaymentdate(){
        return paymentdate;
    }
}
