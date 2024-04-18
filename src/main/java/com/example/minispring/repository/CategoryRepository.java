package com.example.minispring.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


import com.example.minispring.model.Category;
import com.example.minispring.model.Request.CategoryRequest;

@Mapper
public interface CategoryRepository {
    @Results(id = "CategoryMapper",value = {
        @Result(property = "categoryId",column = "category_id"),
        @Result(property = "categoryName",column = "name"),
        @Result(property = "categoryDescription",column = "description"),
        @Result(property = "userId",column = "user_id")

    })
    @Select("""
        select * from categories_tb inner JOIN users_tb on categories_tb.user_id =users_tb.user_id where users_tb.email = #{email}
    """)
    List<Category> getAllCategory(String email);

    @Select("""
        select c.* from categories_tb c inner JOIN users_tb u on c.user_id =u.user_id
        where u.email ='a@gmail.com' AND c.category_id = #{categoryId}
    """)
    @ResultMap("CategoryMapper")
    Category getCategoryById(String email, Integer categoryId);

    @Select("""
        insert into categories_tb(name,description,user_id) values(#{category.categoryName},#{category.categoryDescription},#{userId}) returning *;      
    """)
    @ResultMap("CategoryMapper")
    Category addNewCategory(@Param("category") CategoryRequest categoryRequest,Integer userId);

    @Select("""
        update categories_tb set name = #{category.categoryName}, description = #{category.categoryDescription} where category_id = #{categoryId} AND user_id = #{userId} returning *; 
    """)
    @ResultMap("CategoryMapper")
    Category updateCategoryById(@Param("category")CategoryRequest categoryRequest,Integer categoryId,Integer userId);

    @Select("""
        delete from categories_tb where category_id = #{categoryId} AND user_id = #{userId} returning *; 
    """)
    @ResultMap("CategoryMapper")
    Category deleteCategoryById(Integer categoryId,Integer userId);
}
