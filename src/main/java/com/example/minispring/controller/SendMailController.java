package com.example.minispring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.minispring.service.EmailSenderService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/mail")
public class SendMailController {
    private final EmailSenderService emailSenderService;

    public SendMailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @GetMapping
    public void sendMail() {
        int randomNumber = (int) (Math.random() * 1000000);
        String formattedNumber = String.format("%05d", randomNumber);
        System.out.println(formattedNumber);
        emailSenderService.sendEmail("testing011testing@gmail.com","Testing","Testing");
    }
}
