package com.mybatis.springminiproject.service.serviceImpl;
import com.mybatis.springminiproject.model.Users;
import com.mybatis.springminiproject.model.dto.request.MailRequest;
import com.mybatis.springminiproject.model.dto.request.AppUserRequest;
import com.mybatis.springminiproject.repository.AppUserRepository;
import com.mybatis.springminiproject.service.AppUserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class AppUserServiceImpl implements AppUserService  {

    private final AppUserRepository appUserRepository;
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email);
    }

    public void register(AppUserRequest appUserRequest){
        appUserRepository.register(appUserRequest);
    }
}