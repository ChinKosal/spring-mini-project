package com.mybatis.springminiproject.repository;
import com.mybatis.springminiproject.model.Categories;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CategoriesRepository {
    @Results(id = "catMapper", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "user", column = "user_id", one = @One(select = "com.mybatis.springminiproject.repository.UserRepository.getUserById"))
    })
    @Select("select * from categories_tb LIMIT #{limit} OFFSET #{offset}")
    List<Categories> getAllCategories(Integer offset, Integer limit);


    @Select("select * from categories_tb where category_id=#{id}")
    @ResultMap("catMapper")
    Categories getCategoryById(Integer id);
}
