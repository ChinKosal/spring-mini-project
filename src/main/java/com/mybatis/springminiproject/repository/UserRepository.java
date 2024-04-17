package com.mybatis.springminiproject.repository;
import com.mybatis.springminiproject.model.dto.response.UserResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRepository {
    @Results(id = "userMapper",value = {
            @Result(property = "userId",column = "user_id"),
            @Result(property = "profileImage",column = "profile_image")
    })

    @Select("select * from users_tb")
    List<UserResponse> getAllUsers();

    @Select("select * from users_tb where user_id = #{id}")
    @ResultMap("userMapper")
    UserResponse getUserById(Integer id);
}
