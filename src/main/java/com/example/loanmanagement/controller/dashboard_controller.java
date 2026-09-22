package com.example.loanmanagement.controller;

import com.example.loanmanagement.DashboardResponse;
import com.example.loanmanagement.service.dashboard_service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@Tag(name = "Dashboard", description = "Loan management dashboard APIs")
public class dashboard_controller {

    @Autowired
    dashboard_service ser;

    @GetMapping
    @Operation(summary = "Get loan management dashboard")
    public ResponseEntity<DashboardResponse> getdashboard(){
        DashboardResponse d = ser.getdashboard();
        return new ResponseEntity<>(d, HttpStatus.OK);
    }
}
