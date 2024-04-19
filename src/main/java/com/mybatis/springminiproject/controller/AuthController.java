package com.mybatis.springminiproject.controller;

import com.mybatis.springminiproject.jwt.JwtService;
import com.mybatis.springminiproject.model.AppUser;
import com.mybatis.springminiproject.model.dto.request.AuthRequest;
import com.mybatis.springminiproject.model.dto.request.AppUserRequest;
import com.mybatis.springminiproject.model.dto.response.AuthResponse;
import com.mybatis.springminiproject.repository.AppUserRepository;
import com.mybatis.springminiproject.service.AppUserService;
import com.mybatis.springminiproject.service.EmailSenderService;
import com.mybatis.springminiproject.service.FileService;
import com.mybatis.springminiproject.service.OptsService;
import jakarta.mail.MessagingException;
import org.apache.coyote.BadRequestException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/auths")
//@SecurityRequirement(name = "bearerAuth")
public class AuthController {
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final FileService fileService;
    private final EmailSenderService emailSenderService;
    private final OptsService optsService;

    public AuthController(AppUserService appUserService, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, EmailSenderService mailService1, AppUserRepository appUserRepository, FileService fileService, EmailSenderService emailSenderService, OptsService optsService) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
        this.fileService = fileService;
        this.emailSenderService = emailSenderService;
        this.optsService = optsService;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            UserDetails userApp = appUserService.loadUserByUsername(username);
            if (userApp == null) {
                throw new BadRequestException("Wrong Email");
            }
            if (!passwordEncoder.matches(password, userApp.getPassword())) {
                throw new BadRequestException("Wrong Password");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        authenticate(authRequest.getEmail(), authRequest.getPassword());
        final UserDetails userDetails = appUserService.loadUserByUsername(authRequest.getEmail());
        final String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUserRequest appUserRequest){
        boolean check = true;
        if(!appUserRequest.getPassword().equals(appUserRequest.getConfirmPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match");
        }
        String encryptedPassword = passwordEncoder.encode(appUserRequest.getPassword());
        appUserRequest.setPassword(encryptedPassword);
        try {
            Resource resource = fileService.getFileByFileName(appUserRequest.getProfileImage());
        }catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image Not found");
        }
        if(check){
            int randomNumber = (int) (Math.random() * 1000000);
            String formattedNumber = String.format("%06d", randomNumber);
            try {
                emailSenderService.register(appUserRequest.getEmail(),"Verify", formattedNumber);
            } catch (MessagingException e) {
                System.out.println("Error sending email: " + e.getMessage());
            }
            appUserService.register(appUserRequest);
            AppUser userId = appUserRepository.findByEmail(appUserRequest.getEmail());
            optsService.insertOpt(formattedNumber, false, userId.getId());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Registration Successfully. Please verify your registration");
    }
}
