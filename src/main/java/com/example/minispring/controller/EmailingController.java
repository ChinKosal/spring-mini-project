package com.example.minispring.controller;

import jakarta.mail.MessagingException;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.minispring.model.Request.MailRequest;
import com.example.minispring.service.EmailingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class EmailingController {
    private final EmailingService emailingService;
        
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendMail(@RequestBody MailRequest request) throws MessagingException {
        emailingService.sendMail(request);
    }
}
