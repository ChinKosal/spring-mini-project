package com.mybatis.springminiproject.service;

import com.mybatis.springminiproject.model.Categories;
import java.util.List;
public interface CategoriesService {
    List<Categories> getAllCategories(Integer offset, Integer limit);

    Categories getCategoryById(Integer id);
}
