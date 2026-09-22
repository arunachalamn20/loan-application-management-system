package com.example.loanmanagement.service;

import com.example.loanmanagement.DashboardResponse;
import com.example.loanmanagement.entity.Loan_application;
import com.example.loanmanagement.repository.loan_application_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class dashboard_service {

    @Autowired
    loan_application_repository rep;

    public DashboardResponse getdashboard(){

        DashboardResponse d = new DashboardResponse();
        d.setTotalapplications(rep.count());
        d.setApprovedloans(rep.countByStatus("Approved"));
        d.setPendingloans(rep.countByStatus("Pending"));
        d.setRejectedloans(rep.countByStatus("Rejected"));
        d.setClosedloans(rep.countByStatus("Closed"));

        Double total = 0.0;
        List<Loan_application> loans = rep.findAll();
        for(Loan_application loan : loans){
            total = total+loan.getLoanamount();
        }

        d.setTotalloanamount(total);
        return d;

    }
}
