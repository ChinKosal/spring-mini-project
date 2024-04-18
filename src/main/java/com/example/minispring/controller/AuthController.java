package com.example.minispring.controller;

import org.apache.coyote.BadRequestException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.minispring.jwt.JwtService;
import com.example.minispring.model.AppUser;
import com.example.minispring.model.Request.AuthRequest;
import com.example.minispring.model.Response.AuthResponse;
import com.example.minispring.service.AppUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
   private final AppUserService appUserService;
   private final BCryptPasswordEncoder passwordEncoder;
   private final AuthenticationManager authenticationManager;
   private final JwtService jwtService;

   public AuthController(AppUserService appUserService, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
    this.appUserService = appUserService;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
   }
   private void authenticate(String username, String password) throws Exception {
   try {
       UserDetails userApp = appUserService.loadUserByUsername(username);
       if (userApp == null){throw new BadRequestException("Wrong Email");}
       if (!passwordEncoder.matches(password, userApp.getPassword())){
           throw new BadRequestException("Wrong Password");}
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
   } catch (DisabledException e) {
       throw new Exception("USER_DISABLED", e);} catch (BadCredentialsException e) {
       throw new Exception("INVALID_CREDENTIALS", e);}}

   @PostMapping("/login")
   public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) throws Exception {
       authenticate(authRequest.getEmail(), authRequest.getPassword());
       final UserDetails userDetails = appUserService.loadUserByUsername(authRequest.getEmail());
       final String token = jwtService.generateToken(userDetails);
       AuthResponse authResponse = new AuthResponse(token);
       return ResponseEntity.ok(authResponse);
   }
   public static String getUsernameOfCurrentUser() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
    String username = userDetails.getUsername();
    return username;
 }
}
