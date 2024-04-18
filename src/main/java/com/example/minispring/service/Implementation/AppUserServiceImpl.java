package com.example.minispring.service.Implementation;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.minispring.repository.AppUserRepository;
import com.example.minispring.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService{
   private final AppUserRepository appUserRepository;
   public AppUserServiceImpl(AppUserRepository appUserRepository) {
       this.appUserRepository = appUserRepository;
   }
   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return appUserRepository.findByEmail(email);
   }
}
