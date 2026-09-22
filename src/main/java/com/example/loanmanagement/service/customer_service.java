package com.example.loanmanagement.service;

import com.example.loanmanagement.entity.Customer;
import com.example.loanmanagement.repository.customer_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class customer_service {

    @Autowired
    customer_repository rep;

    //create customer
    public Customer create(Customer customer){
        return rep.save(customer);
    }

    //get customer by id
    public Customer getbyid(Long id){
        return rep.findById(id).orElse(null);
    }

    //get all customers
    public List<Customer> getall(){

        return rep.findAll();

    }

    //update customer
    public Customer update(Long id,Customer customer){

        Customer existing_customer = rep.findById(id).orElse(null);
        if(existing_customer!=null){
            existing_customer.setName(customer.getName());
            existing_customer.setEmail(customer.getEmail());
            existing_customer.setPhone(customer.getPhone());
            existing_customer.setAddress(customer.getAddress());

            return rep.save(existing_customer);
        }
        return null;

    }

    //delete customer by id
    public void deletebyid(Long id){

        rep.deleteById(id);
    }

    //delete all customers
    public void deleteall(){
        rep.deleteAll();
    }

    //find customer by names
    public List<Customer> getbyname(String name){
        return rep.findByName(name);
    }

    //find customer by email
    public Customer getbyemail(String email){
        return rep.findByEmail(email);
    }


}
