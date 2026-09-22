package com.example.loanmanagement.controller;


import com.example.loanmanagement.entity.Loan_application;
import com.example.loanmanagement.service.loan_application_service;
import com.sun.net.httpserver.HttpsServer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
@Tag(name = "Loan Management", description = "APIs for managing loan applications")
public class loan_application_controller {

    @Autowired
    loan_application_service ser;

    //create a loan application
    @Operation(summary = "Create a loan application")
    @PostMapping("/create/{customerid}")
    public ResponseEntity<Loan_application> create(@PathVariable Long customerid, @Valid @RequestBody Loan_application loan){

        Loan_application createdloan = ser.create_loan(customerid,loan);
        if(createdloan==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createdloan,HttpStatus.CREATED);
    }

    //get loan application by id
    @Operation(summary = "Get loan application by ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<Loan_application> getbyid(@PathVariable Long id){

        Loan_application loan = ser.getbyid(id);
        if(loan==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loan,HttpStatus.OK);
    }

    //get all loan applications
    @GetMapping("/get")
    @Operation(summary = "Get all loan applications")
    public ResponseEntity<List<Loan_application>> getall(){
        List<Loan_application> loan = ser.getall();
        if(loan.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    //update loan application by loan id
    @PutMapping("/update/{id}")
    @Operation(summary = "Update loan application by loan id")
    public ResponseEntity<Loan_application> update(@PathVariable Long id,@Valid @RequestBody Loan_application loan){
        Loan_application updatedloan = ser.update(id,loan);
        if(updatedloan==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedloan,HttpStatus.OK);

    }

    //delete loan application by id
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete loan application by id")
    public ResponseEntity<String> deletebyid(@PathVariable Long id){
        Loan_application loan = ser.getbyid(id);
        if(loan==null){
            return new ResponseEntity<>("Loan not found",HttpStatus.NOT_FOUND);
        }
        ser.deletebyid(id);
        return new ResponseEntity<>("Loan deleted successfully",HttpStatus.OK);
    }

    //delete all loan applications
    @DeleteMapping("/delete")
    @Operation(summary = "Delete all loan application")
    public ResponseEntity<String> delete(){
        ser.deleteall();
        return new ResponseEntity<>("All loans deleted successfully",HttpStatus.OK);
    }

    //update status in loan applications
    @PutMapping("/update/status/{id}")
    @Operation(summary = "Update loan application status by load id")
    public ResponseEntity<Loan_application> updatestatus(@PathVariable Long id,@RequestBody Loan_application loan){
        Loan_application l = ser.updatestatus(id,loan.getStatus());
        if(l==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(l,HttpStatus.OK);

    }

    //get loan applications by status

    @GetMapping("/status/{status}")
    @Operation(summary = "Search loans by status")
    public ResponseEntity<List<Loan_application>> getbystatus(@PathVariable String status){
        if(!(status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("Rejected") || status.equalsIgnoreCase("Closed"))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Loan_application> loans = ser.getbystatus(status);
        if(loans.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loans,HttpStatus.OK);

    }

    //get loan applications by loan type

    @GetMapping("/loantype/{loantype}")
    @Operation(summary = "Search loans by loan type")
    public ResponseEntity<List<Loan_application>> getbyloantype(@PathVariable String loantype){
        if(!(loantype.equalsIgnoreCase("Personal") || loantype.equalsIgnoreCase("Education") || loantype.equalsIgnoreCase("Home") || loantype.equalsIgnoreCase("Vehicle"))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Loan_application> loans = ser.getbyloantype(loantype);
        if(loans.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loans,HttpStatus.OK);

    }

    //get loan applications by customer name
    @Operation(summary = "Search loans by customer name")
    @GetMapping("/customer/{name}")
    public ResponseEntity<List<Loan_application>> getbycustomername(@PathVariable String name){
        List<Loan_application> loan = ser.getbycustomername(name);
        if(loan.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loan,HttpStatus.OK);
    }

    //get latest loan applications
    @Operation(summary = "Get latest loan applications")
    @GetMapping("/latest")
    public ResponseEntity<List<Loan_application>> getlatestloans(){
        List<Loan_application> loans = ser.getlatestloans();
        return new ResponseEntity<>(loans,HttpStatus.OK);
    }

    //count loan applications by status
    @Operation(summary = "Count loan application by status")
    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> countbystatus(@PathVariable String status){
        if(!(status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("Rejected") || status.equalsIgnoreCase("Closed"))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long count = ser.countbystatus(status);

        return new ResponseEntity<>(count,HttpStatus.OK);

    }

    //count loan applications by loan type
    @Operation(summary = "Count loan applications by loan type")
    @GetMapping("/count/loantype/{loantype}")
    public ResponseEntity<Long> countbyloantype(@PathVariable String loantype){
        if(!(loantype.equalsIgnoreCase("Personal") || loantype.equalsIgnoreCase("Education") || loantype.equalsIgnoreCase("Home") || loantype.equalsIgnoreCase("Vehicle"))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long count = ser.countbyloantype(loantype);

        return new ResponseEntity<>(count,HttpStatus.OK);

    }


}
