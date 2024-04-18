package com.mybatis.springminiproject.repository;
import com.mybatis.springminiproject.model.AppUser;
import com.mybatis.springminiproject.model.Users;
import com.mybatis.springminiproject.model.dto.request.UserRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.User;

@Mapper
public interface AppUserRepository {
    @Select("""
           SELECT * FROM users_tb WHERE email = #{email}
           """)
    @Results(id = "userMap", value = {
            @Result(property = "id", column = "id"),
    })
    AppUser findByEmail(String email);

    @Select("""
        INSERT INTO users_tb (email, password, profile_image) VALUES (#{users.email} , #{users.password} , #{users.profileImage}) RETURNING *;
    """)
    Users register(@Param("users") UserRequest userRequest);
}
