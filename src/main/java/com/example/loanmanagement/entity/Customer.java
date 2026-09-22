package com.example.loanmanagement.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Pattern(
            regexp = "[a-zA-Z ]+",
            message = "Name must contain only alphabets and spaces"
    )
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "[0-9]{10}",
            message = "phone nust have exactly 10 digits"
    )
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    //id - no need to write for setId(),because msql will automatically generate it,this prevents the user from sending the id
    public Long getId(){
        return id;
    }

    //name
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    //email
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    //phone
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return phone;
    }

    //address
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }




}
