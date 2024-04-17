package com.mybatis.springminiproject.service.serviceImpl;

import com.mybatis.springminiproject.model.dto.response.UserResponse;
import com.mybatis.springminiproject.repository.UserRepository;
import com.mybatis.springminiproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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
        return userRepository.getUserById(id);
    }

}
