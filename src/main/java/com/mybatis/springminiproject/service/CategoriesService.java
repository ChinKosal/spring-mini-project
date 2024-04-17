package com.mybatis.springminiproject.service;

import com.mybatis.springminiproject.model.Categories;
import com.mybatis.springminiproject.model.dto.request.CategoriesRequest;

import java.util.List;
public interface CategoriesService {
    List<Categories> getAllCategories(Integer offset, Integer limit);

    Categories getCategoryById(Integer id);

    Categories insertCategory(CategoriesRequest categoriesRequest);

    Categories updateCategory(Integer id, CategoriesRequest categoriesRequest);

    Categories deleteCategoryById(Integer id);
}
