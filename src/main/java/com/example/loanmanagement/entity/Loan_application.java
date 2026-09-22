package com.example.loanmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
public class Loan_application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;
    //this means many loan applications can point to one customer
    //many refers to the entity where it is created,here many points to Loan_application entity
    //one points to entity below the @manytoone annotation,here one points to customer

    @NotBlank(message = "Loan Type is required")
    @Pattern(
            regexp = "Personal|Education|Home|Vehicle",
            message = "Loan Type should be Personal,Education,Home or Vehicle"
    )
    private String loantype;

    @NotNull(message = "Loan amount is required")
    @DecimalMin(value = "1000",message = "Minimum loan amount is 1000")
    private Double loanamount;

    @NotNull(message = "Tenure is required")
    private Integer tenuremonth;

    private Double interestrate;

    private String status;

    private LocalDateTime applicationdate;

    public Loan_application(){

    }

    //id - no need to write for setId(),because msql will automatically generate it,this prevents the user from sending the id
    public Long getId(){
        return id;
    }

    //customer
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    public Customer getCustomer(){
        return customer;
    }

    //loantype
    public void setLoantype(String loantype){
        this.loantype = loantype;
    }
    public String getLoantype(){
        return loantype;
    }

    //loanamount
    public void setLoanamount(Double loanamount){
        this.loanamount = loanamount;
    }
    public Double getLoanamount(){
        return loanamount;
    }

    //tenure
    public void setTenuremonth(Integer tenuremonth){
        this.tenuremonth = tenuremonth;
    }
    public Integer getTenuremonth(){
        return tenuremonth;
    }

    //interest rate
    public void setInterestrate(Double interestrate){
        this.interestrate = interestrate;
    }
    public Double getInterestrate(){
        return interestrate;
    }

    //status
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }

    //localdatetime
    public void setApplicationdate(LocalDateTime applicationdate){
        this.applicationdate = applicationdate;
    }
    public LocalDateTime getApplicationdate(){
        return applicationdate;
    }


}
