package com.mybatis.springminiproject.service.serviceImpl;

import com.mybatis.springminiproject.model.Users;
import com.mybatis.springminiproject.model.dto.request.UserRequest;
import com.mybatis.springminiproject.repository.AppUserRepository;
import com.mybatis.springminiproject.service.AppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public Users register(UserRequest userRequest) {
        try {
            String password = userRequest.getPassword();
            String confirmPassword = userRequest.getConfirmPassword();

            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Password and confirm password do not match");
            }

            // Rest of the registration logic
            return appUserRepository.register(userRequest);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Registration failed: " + e.getMessage());
        }
    }
}
