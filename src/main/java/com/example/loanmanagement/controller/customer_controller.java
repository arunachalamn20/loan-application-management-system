package com.example.loanmanagement.controller;


import com.example.loanmanagement.entity.Customer;
import com.example.loanmanagement.service.customer_service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer Management",description = "APIs for managing customers")
public class customer_controller {

    @Autowired
    customer_service ser;

    //create new customer
    @Operation(summary = "Create a customer")
    @PostMapping("/create")
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) {
        Customer c = ser.create(customer);
        return new ResponseEntity<>(c,HttpStatus.OK);

    }

    //get customer by id
    @Operation(summary = "Get customer by ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<Customer> getbyid(@PathVariable Long id) {
        Customer c = ser.getbyid(id);
        if (c == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    //get all customers
    @Operation(summary = "Get all customers")
    @GetMapping("/get")
    public ResponseEntity<List<Customer>> getall() {
        return new ResponseEntity<>(ser.getall(),HttpStatus.OK);
    }

    //update customer
    @PutMapping("/update/{id}")
    @Operation(summary = "Update customer")
    public ResponseEntity<Customer> update(Long id,@Valid @RequestBody Customer customer){
        Customer c = ser.update(id,customer);
        if(c==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    //delete customer by id
    @Operation(summary = "Delete customer by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletebyid(@PathVariable Long id){
        Customer c = ser.getbyid(id);
        if(c==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ser.deletebyid(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }

    //delete all customers
    @Operation(summary = "Delete all customer")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteall(){
        ser.deleteall();
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }

    //find customer by name
    @Operation(summary = "Search customer by name")
    @GetMapping("name/{name}")
    public ResponseEntity<List<Customer>> getbyname(@PathVariable String name){
        List<Customer> c = ser.getbyname(name);
        if(c.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    //find customer by email
    @Operation(summary = "Search customer by email")
    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getbyemail(@PathVariable String email){
        Customer c = ser.getbyemail(email);
        if(c==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
}

