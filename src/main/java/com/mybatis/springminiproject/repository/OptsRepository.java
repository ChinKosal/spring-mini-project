package com.mybatis.springminiproject.repository;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OptsRepository {
    @Select("""
        insert into opts_tb(opt_code,issued_at,expiration,verify,user_id) values(#{optCode},#{issued},#{expiration},#{verify},#{userId})
    """)
    void insertOpt(String optCode,LocalDateTime issued,LocalDateTime expiration, Boolean verify, Integer userId);
}
