package com.mybatis.springminiproject.repository;
import com.mybatis.springminiproject.model.AppUser;
import com.mybatis.springminiproject.model.Users;
import com.mybatis.springminiproject.model.dto.request.AppUserRequest;
import com.mybatis.springminiproject.model.dto.response.UserResponse;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AppUserRepository {
    @Select("""
           SELECT * FROM users_tb WHERE email = #{email}
           """)
    @Results(id = "UserMap", value = {
            @Result(property = "id", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "image", column = "profile_image")
    })
    AppUser findByEmail(String email);
    @Results(id = "UserResponseMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "profileImage", column = "profile_image")
    })
    @Select("""
        select * from users_tb where user_id = #{userId}                
""")
    UserResponse findUserById(Integer userId);

    @Select("""
        insert into users_tb(email,password,profile_image) values (#{user.email}, #{user.password}, #{user.profileImage})
    """)
    Users register(@Param("user") AppUserRequest userRequest);
}
