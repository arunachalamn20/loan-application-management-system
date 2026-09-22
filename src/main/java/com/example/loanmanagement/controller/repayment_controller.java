package com.example.loanmanagement.controller;

import com.example.loanmanagement.entity.Repayment;
import com.example.loanmanagement.service.repayment_service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.invoker.HttpServiceArgumentResolver;

import java.util.List;

@RestController
@RequestMapping("/repayment")
@Tag(name = "Repayment Management", description = "APIs for managing loan repayments")
public class repayment_controller {

    @Autowired
    repayment_service ser;

    //create a repayment
    @Operation(summary = "Record a loan repayment")
    @PostMapping("/create/{loanid}")
    public ResponseEntity<Repayment> create(@PathVariable Long loanid, @Valid @RequestBody Repayment repayment){
        Repayment createdrepayment = ser.create(loanid,repayment);
        if(createdrepayment==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createdrepayment,HttpStatus.CREATED);
    }

    //get repayment by id
    @Operation(summary = "Get repayment by repayment ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<Repayment> getbyid(@PathVariable Long id){
        Repayment repayment = ser.getbyid(id);
        if(repayment==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(repayment,HttpStatus.OK);
    }

    //get all repayments
    @Operation(summary = "Get all repayments")
    @GetMapping("/get")
    public ResponseEntity<List<Repayment>> getall(){
        List<Repayment> repayments = ser.getall();
        return new ResponseEntity<>(repayments,HttpStatus.OK);
    }

    //update a repayment by repayment id
    @Operation(summary = "Update repayment by repayment id")
    @PutMapping("/update/{id}")
    public ResponseEntity<Repayment> update(@PathVariable Long id,@Valid @RequestBody Repayment repayment){
        Repayment updatedrepayment = ser.update(id,repayment);
        if(updatedrepayment==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedrepayment,HttpStatus.OK);
    }

    //delete repayment by id
    @Operation(summary = "Delete repayment by repayment id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletebyid(@PathVariable Long id){
        Repayment repayment = ser.getbyid(id);
        if(repayment==null){
            return new ResponseEntity<>("Repayment now found",HttpStatus.NOT_FOUND);
        }
        ser.deletebyid(id);
        return new ResponseEntity<>("Repayment deleted successfully",HttpStatus.OK);
    }

    //delete all repayments
    @Operation(summary = "Delete all repayment")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteall(){
        ser.deleteall();
        return new ResponseEntity<>("Deleted all repayments successfully",HttpStatus.OK);
    }

    //get repayments by loan id
    @Operation(summary = "Get all repayments for a loan")
    @GetMapping("/loan/{id}")
    public ResponseEntity<List<Repayment>> getbyloanid(@PathVariable Long id){
        List<Repayment> repayments = ser.getbyloanid(id);
        if(repayments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(repayments,HttpStatus.OK);
    }

    //get total amount paid by loan id
    @Operation(summary = "Calculate total amount paid for a loan")
    @GetMapping("/total/{loadid}")
    public ResponseEntity<Double> gettotalpaid(@PathVariable Long loadid){
        Double totalpaid = ser.gettotalpaid(loadid);
        if(totalpaid==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(totalpaid,HttpStatus.OK);
    }

    //get remaining to be paid
    @Operation(summary = "Calculate remaining loan amount")
    @GetMapping("/remaining/{loanid}")
    public ResponseEntity<Double> getremainingamount(@PathVariable Long loanid){
        Double remaining = ser.getremainingamount(loanid);
        if(remaining==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(remaining,HttpStatus.OK);
    }

}
