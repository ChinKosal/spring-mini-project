package com.mybatis.springminiproject.service;
import com.mybatis.springminiproject.model.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponse> getAllUsers();

    UserResponse getUserById(Integer id);
}
