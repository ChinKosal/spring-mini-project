package com.mybatis.springminiproject.controller;
import com.mybatis.springminiproject.model.Categories;
import com.mybatis.springminiproject.model.dto.response.ApiResponse;
import com.mybatis.springminiproject.service.CategoriesService;
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
    public ResponseEntity<?> getAllCategories(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "5")Integer limit) {
        ApiResponse<List<Categories>> response = ApiResponse.<List<Categories>>builder()
                .message("Get all categories successfully.")
                .payload(categoriesService.getAllCategories(offset,limit))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        ApiResponse<Categories> response = ApiResponse.<Categories>builder()
                .message("Get category by id successfully")
                .payload(categoriesService.getCategoryById(id))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
