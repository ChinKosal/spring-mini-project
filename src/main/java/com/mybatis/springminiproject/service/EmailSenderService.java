package com.mybatis.springminiproject.service;


import jakarta.mail.MessagingException;

public interface EmailSenderService {
    void register(String email,String subject,String formattedNumber) throws MessagingException;
}
