package com.mybatis.springminiproject.repository;

import com.mybatis.springminiproject.model.AppUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AppUserRepository {
    @Select("""
           SELECT * FROM users_tb WHERE email = #{email}
           """)
    @Results(id = "userMap", value = {
            @Result(property = "id", column = "id"),
    })
    AppUser findByEmail(String email);
}
