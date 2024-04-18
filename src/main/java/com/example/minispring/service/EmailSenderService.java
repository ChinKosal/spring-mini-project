package com.example.minispring.service;

public interface EmailSenderService {
    void sendEmail(String toEmail, String subject, String text);
}
