package com.example.minispring.service.Implementation;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.minispring.model.AppUser;
import com.example.minispring.model.Opts;
import com.example.minispring.model.Request.AppUserRequest;
import com.example.minispring.model.Response.AppUserResponse;
import com.example.minispring.repository.AppUserRepository;
import com.example.minispring.repository.OptsRepository;
import com.example.minispring.service.AppUserService;
import com.example.minispring.validation.NotFound;

@Service
public class AppUserServiceImpl implements AppUserService{
   private final AppUserRepository appUserRepository;
   private final OptsRepository optsRepository;
   public AppUserServiceImpl(AppUserRepository appUserRepository,OptsRepository optsRepository) {
       this.appUserRepository = appUserRepository;
       this.optsRepository = optsRepository;
   }
   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    AppUser appUser = appUserRepository.findByEmail(email);
    System.out.println(appUser);
    if(appUser == null){
        throw new NotFound("Email not found");
    }
    Opts opt = optsRepository.checkUserId(appUser.getId());
    System.out.println(opt);
    if(opt.getVerify() == false) {
        throw new NotFound("Your account has not been verified");
    }
    return appUser;
   }

   public void saveUser(AppUserRequest appUserRequest){
        appUserRepository.saveUser(appUserRequest);
   }
}
