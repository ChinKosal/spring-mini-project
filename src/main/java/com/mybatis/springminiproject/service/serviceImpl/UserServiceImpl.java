package com.mybatis.springminiproject.service.serviceImpl;

import com.mybatis.springminiproject.model.response.UserResponse;
import com.mybatis.springminiproject.repository.UserRepository;
import com.mybatis.springminiproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public UserResponse getUserById(Integer id) {
//        UUID userId = UUID.fromString(String.valueOf(id));
        return userRepository.getUserById(id);
    }


}
