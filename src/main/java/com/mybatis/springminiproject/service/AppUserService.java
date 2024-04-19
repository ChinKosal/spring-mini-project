package com.mybatis.springminiproject.service;


import com.mybatis.springminiproject.model.Users;
import com.mybatis.springminiproject.model.dto.request.AppUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    void register(AppUserRequest userRequest);
}