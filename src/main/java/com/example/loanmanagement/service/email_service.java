package com.example.loanmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class email_service {

    @Autowired
    JavaMailSender mailsender;

    // Loan submitted
    public void sendLoanSubmittedMail(String email, String name, Long loanId) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Loan Application Submitted");

        message.setText(
                "Dear " + name + ",\n\n" +
                        "Your loan application has been successfully submitted.\n\n" +
                        "Loan Application ID: " + loanId + "\n" +
                        "Status: Pending\n\n" +
                        "We will notify you when there is an update on your application.\n\n" +
                        "Regards,\n" +
                        "Loan Management System"
        );

        mailsender.send(message);
    }

    // Loan approved
    public void sendLoanApprovedMail(String email, String name, Long loanId) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Loan Application Approved");

        message.setText(
                "Dear " + name + ",\n\n" +
                        "Congratulations! Your loan application has been approved.\n\n" +
                        "Loan Application ID: " + loanId + "\n" +
                        "Status: Approved\n\n" +
                        "Regards,\n" +
                        "Loan Management System"
        );

        mailsender.send(message);
    }

    // Loan rejected
    public void sendLoanRejectedMail(String email, String name, Long loanId) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Loan Application Rejected");

        message.setText(
                "Dear " + name + ",\n\n" +
                        "We regret to inform you that your loan application has been rejected.\n\n" +
                        "Loan Application ID: " + loanId + "\n" +
                        "Status: Rejected\n\n" +
                        "Regards,\n" +
                        "Loan Management System"
        );

        mailsender.send(message);
    }

    // Loan closed
    public void sendLoanClosedMail(String email, String name, Long loanId) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Loan Application Closed");

        message.setText(
                "Dear " + name + ",\n\n" +
                        "Your loan application has been closed.\n\n" +
                        "Loan Application ID: " + loanId + "\n" +
                        "Status: Closed\n\n" +
                        "Thank you for using our Loan Management System.\n\n" +
                        "Regards,\n" +
                        "Loan Management System"
        );

        mailsender.send(message);
    }
}
