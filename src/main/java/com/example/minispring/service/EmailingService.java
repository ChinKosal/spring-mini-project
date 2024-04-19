package com.example.minispring.service;

import com.example.minispring.model.Request.MailRequest;

import jakarta.mail.MessagingException;

public interface EmailingService {
    void sendMail(MailRequest request) throws MessagingException;
}
