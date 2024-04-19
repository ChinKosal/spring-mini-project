package com.example.minispring.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.minispring.model.Request.AppUserRequest;

public interface AppUserService extends UserDetailsService {
    void saveUser(AppUserRequest appUserRequest);
}