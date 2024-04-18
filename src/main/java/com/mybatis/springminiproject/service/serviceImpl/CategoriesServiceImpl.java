package com.mybatis.springminiproject.service.serviceImpl;
import com.mybatis.springminiproject.exception.NotFoundException;
import com.mybatis.springminiproject.model.Categories;
import com.mybatis.springminiproject.model.dto.request.CategoriesRequest;
import com.mybatis.springminiproject.repository.CategoriesRepository;
import com.mybatis.springminiproject.service.CategoriesService;
import jakarta.validation.constraints.Positive;
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
        Categories categories = categoriesRepository.getCategoryById(id);
        if(categories==null){
            throw new NotFoundException("category id "+id+" not found!!!");
        }
        return categoriesRepository.getCategoryById(id);
    }

    @Override
    public Categories insertCategory( CategoriesRequest categoriesRequest) {
        Categories categories = categoriesRepository.getCategoryById(categoriesRequest.getUserId());
        if(categories==null){
            throw new NotFoundException("User id "+categoriesRequest.getUserId()+" not found!!!");
        }
        return categoriesRepository.insertCategory(categoriesRequest);
    }

    @Override
    public Categories updateCategory(Integer id, CategoriesRequest categoriesRequest) {
        Categories categories = categoriesRepository.getCategoryById(id);
        if(categories==null){
            throw new NotFoundException("Category id "+id+" not found!!!");
        }
        return categoriesRepository.updateCategory(id,categoriesRequest);
    }

    @Override
    public Categories deleteCategoryById(Integer id) {
        Categories categories = categoriesRepository.getCategoryById(id);
        if(categories==null){
            throw new NotFoundException("category id "+id+" not found!!!");
        }
        return categoriesRepository.deleteCategoryById(id);
    }
}
