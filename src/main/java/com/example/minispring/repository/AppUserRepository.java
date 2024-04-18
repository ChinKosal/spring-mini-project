package com.example.minispring.repository;

import org.apache.ibatis.annotations.*;

import com.example.minispring.model.AppUser;

@Mapper
public interface AppUserRepository {
   @Select("""
           SELECT * FROM users_tb WHERE email = #{email}
           """)
    @Results(id = "userMap", value = {
           @Result(property = "id", column = "user_id")
   })
   AppUser findByEmail(String email);
}
