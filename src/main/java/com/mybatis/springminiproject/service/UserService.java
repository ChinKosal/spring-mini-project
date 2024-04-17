package com.mybatis.springminiproject.service;
import com.mybatis.springminiproject.model.dto.response.UserResponse;

import java.util.List;
public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Integer id);
}
