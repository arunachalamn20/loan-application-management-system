package com.example.loanmanagement.service;

import com.example.loanmanagement.entity.Customer;
import com.example.loanmanagement.entity.Loan_application;
import com.example.loanmanagement.repository.customer_repository;
import com.example.loanmanagement.repository.loan_application_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class loan_application_service {

    @Autowired
    loan_application_repository loan_rep;

    @Autowired
    email_service email_ser;

    @Autowired
    customer_repository customer_rep;

    //create loan application
    public Loan_application create_loan(Long customerId, Loan_application loan){

        Customer customer = customer_rep.findById(customerId).orElse(null);
        if(customer==null){
            return null;
        }

        loan.setCustomer(customer);
        //this customer reference in loan entity stores this customer object temporarily
        //when saving the loan object in database,the hibernate look at the temporary customer reference in loan object,then the hibernate will take the primary key column from customer reference and insert it into the loan table
        //that is,it will create a column like customer_id in loan table,which acts as foreign key column in loan table
        //this creates a relationship between loan entity and customer entity
        //the foreign key column is always created at entity refering to many(here loan points to many)
        loan.setStatus("Pending");
        loan.setApplicationdate(LocalDateTime.now());

        //store saved loan
        Loan_application savedloan = loan_rep.save(loan);
        //send mail
        email_ser.sendLoanSubmittedMail(customer.getEmail(), customer.getName(), savedloan.getId());
        return savedloan;

    }

    //get loan application by loan id
    public Loan_application getbyid(Long id){
        return loan_rep.findById(id).orElse(null);
    }

    //get all loan applications
    public List<Loan_application> getall(){
        return loan_rep.findAll();
    }

    //update loan application using loan id
    public Loan_application update(Long id,Loan_application loan){

        Loan_application existingloan = loan_rep.findById(id).orElse(null);
        if(existingloan!=null){
            existingloan.setLoanamount(loan.getLoanamount());
            existingloan.setLoantype(loan.getLoantype());
            existingloan.setTenuremonth(loan.getTenuremonth());
            existingloan.setInterestrate(loan.getInterestrate());

            return loan_rep.save(existingloan);
        }
        return null;
    }

    //delete loan applications by id
    public void deletebyid(Long id){
        loan_rep.deleteById(id);
    }

    //delete all loan applications
    public void deleteall(){
        loan_rep.deleteAll();
    }

    //update loan application status
    public Loan_application updatestatus(Long id,String status){

        Loan_application loan = loan_rep.findById(id).orElse(null);
        if(loan==null){
           return null;
        }
        if(!(status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("Closed") || status.equalsIgnoreCase("Rejected"))){
            return null;
        }
        loan.setStatus(status);
        //first save loan in database
        Loan_application updatedloan = loan_rep.save(loan);
        Customer customer = updatedloan.getCustomer();
        // Get the Customer object associated with this loan
        // The loan has a customer reference because of @ManyToOne
        // Conceptually, Hibernate uses the customer_id (foreign key) in the loan table
        // to connect/join with the Customer table with loan table and find the matching customer
        // Then Hibernate loads that customer's details into the Customer object
        // So loan.getCustomer() returns the Customer object associated with this loan

        //conceptually ,
        //SELECT *
        //FROM loan_application l
        //JOIN customer c
        //ON l.customer_id = c.id
        //WHERE l.id = 1 ->filter all rows other than the current loan id,i.e loan id 1;

        // Send email based on new status
        if (status.equalsIgnoreCase("Approved")) {

            email_ser.sendLoanApprovedMail(
                    customer.getEmail(),
                    customer.getName(),
                    loan.getId()
            );

        } else if (status.equalsIgnoreCase("Rejected")) {

            email_ser.sendLoanRejectedMail(
                    customer.getEmail(),
                    customer.getName(),
                    loan.getId()
            );

        } else if (status.equalsIgnoreCase("Closed")) {

            email_ser.sendLoanClosedMail(
                    customer.getEmail(),
                    customer.getName(),
                    loan.getId()
            );
        }

        return updatedloan;

    }

    //get loan applications by status
    public List<Loan_application> getbystatus(String status){
        return loan_rep.findByStatus(status);
    }

    //get loan applications by loan type
    public List<Loan_application> getbyloantype(String loantype){
        return loan_rep.findByLoantype(loantype);
    }

    //get loan by customer name
    public List<Loan_application> getbycustomername(String name){

        return loan_rep.findByCustomerName(name);
        // 1. findByCustomerName("Arun") is called from the Loan entity.
        // 2. JPA looks for customerName column in entity.
        // 3. There is no direct customerName field in the Loan entity.
        // 4. Loan has a customer reference because of @ManyToOne,then jpa look into that customer reference.
        // 5. That customer reference points to the Customer entity.
        // 6. The Customer entity has a name field.
        // 7. So JPA understands it as customer.name,that is take name field from customer table.
        // 8. There is a relationship between Loan and Customer.
        // 9. The Loan table has the customer's foreign key.
        // 10. The Customer table has the customer's primary key.
        // 11. JPA/Hibernate uses this relationship between the foreign key and primary key and make a inner join between them,after joining the resulting table has all columns from both loan and customer table.
        // 12. It checks the name in the Customer table.
        // 13. It finds the Customer whose name matches "Arun".
        // 14. It gives the Loan record(s) associated with that Customer.
    }

    //get all new loan applications
    public List<Loan_application> getlatestloans(){
        return loan_rep.findAllByOrderByApplicationdateDesc();
    }

    //count loan applications by status
    public Long countbystatus(String status){
        return loan_rep.countByStatus(status);
    }

    //count loan applications by loan type
    public Long countbyloantype(String loantype){
        return loan_rep.countByLoantype(loantype);
    }
}
