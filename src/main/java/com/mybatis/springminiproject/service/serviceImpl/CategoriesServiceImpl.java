package com.mybatis.springminiproject.service.serviceImpl;


import com.mybatis.springminiproject.model.Categories;
import com.mybatis.springminiproject.repository.CategoriesRepository;
import com.mybatis.springminiproject.service.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;
    @Override
    public List<Categories> getAllCategories(Integer offset,Integer limit) {
        offset=(offset-1)*limit;
        return categoriesRepository.getAllCategories(offset, limit);
    }

    @Override
    public Categories getCategoryById(Integer id) {
        //Categories categories = categoriesRepository.getCategoryById(id);
        return categoriesRepository.getCategoryById(id);
    }
}
