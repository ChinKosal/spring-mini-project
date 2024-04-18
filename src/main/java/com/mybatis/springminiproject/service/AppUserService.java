package com.mybatis.springminiproject.service;


import com.mybatis.springminiproject.model.AppUser;
import com.mybatis.springminiproject.model.Users;
import com.mybatis.springminiproject.model.dto.request.UserRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    Users register(UserRequest userRequest);
}