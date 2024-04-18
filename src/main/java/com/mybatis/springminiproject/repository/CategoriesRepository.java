package com.mybatis.springminiproject.repository;
import com.mybatis.springminiproject.model.Categories;
import com.mybatis.springminiproject.model.dto.request.CategoriesRequest;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CategoriesRepository {
    @Results(id = "catMapper", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.mybatis.springminiproject.repository.UserRepository.getUserById")),
    })
    @Select("select * from categories_tb LIMIT #{limit} OFFSET #{offset}")
    List<Categories> getAllCategories(Integer offset, Integer limit);


    @Select("select * from categories_tb where category_id=#{id}")
    @ResultMap("catMapper")
    Categories getCategoryById(Integer id);


    @Select("insert into categories_tb (name,description,user_id) values (#{cat.name},#{cat.description},#{cat.userId})  returning *")
    @ResultMap("catMapper")
    Categories insertCategory(@Param("cat") CategoriesRequest categoriesRequest);


    @Select("update categories_tb set name=#{cat.name},description=#{cat.description},user_id=#{cat.userId} where category_id=#{id} returning *")
    @ResultMap("catMapper")
    Categories updateCategory(Integer id,@Param("cat") CategoriesRequest CategoriesRequest);


    @Select("delete  from categories_tb where category_id=#{id}")
    @ResultMap("catMapper")
    Categories deleteCategoryById(Integer id);
}
