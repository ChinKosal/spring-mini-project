package com.mybatis.springminiproject.controller;
import com.mybatis.springminiproject.model.Categories;
import com.mybatis.springminiproject.model.dto.request.CategoriesRequest;
import com.mybatis.springminiproject.model.dto.response.ApiResponse;
import com.mybatis.springminiproject.service.CategoriesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("api/v1/categories")
@AllArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;
    @GetMapping
    public ResponseEntity<?> getAllCategories(@RequestParam(defaultValue = "1") @Positive Integer offset, @RequestParam(defaultValue = "5") @Positive Integer limit) {
        ApiResponse<List<Categories>> response = ApiResponse.<List<Categories>>builder()
                .message("Get all categories successfully.")
                .payload(categoriesService.getAllCategories(offset,limit))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable @Positive Integer id) {
        ApiResponse<Categories> response = ApiResponse.<Categories>builder()
                .message("Get category by id successfully")
                .payload(categoriesService.getCategoryById(id))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<?> insertCategory(@RequestBody @Valid CategoriesRequest categoriesRequest) {
        ApiResponse<Categories> response = ApiResponse.<Categories>builder()
                .message("Insert category successfully")
                .payload(categoriesService.insertCategory(categoriesRequest))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable @Positive Integer id, @RequestBody @Valid CategoriesRequest categoriesRequest) {
        ApiResponse<Categories> response = ApiResponse.<Categories>builder()
                .message("Update category by id successfully")
                .payload(categoriesService.updateCategory(id,categoriesRequest))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable @Positive Integer id) {
        ApiResponse<Categories> response = ApiResponse.<Categories>builder()
                .message("Delete category by id successfully")
                .payload(categoriesService.deleteCategoryById(id))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
