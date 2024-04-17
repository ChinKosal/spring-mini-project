package com.mybatis.springminiproject.repository;


import com.mybatis.springminiproject.config.UUIDTypeHandler;
import com.mybatis.springminiproject.model.Categories;
import com.mybatis.springminiproject.model.response.UserResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CategoriesRepository {
    @Results(id = "catMapper", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "userId", column = "user_id", one = @One(select = "getUserByCategoryId"))
    })

    @Select("select * from categories_tb OFFSET #{offset} LIMIT #{limit}")
    List<Categories> getAllCategories(Integer offset, Integer limit);


    @Select("select ut.user_id,ut.email,ut.profile_image from categories_tb ct inner join users_tb ut on ut.user_id = ct.user_id where ct.category_id=#{id}")
    @ResultMap("catMapper")
    @Results(id = "userMapper", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "profileImage", column = "profile_image")
    })
    UserResponse getUserByCategoryId(Integer id);
}
