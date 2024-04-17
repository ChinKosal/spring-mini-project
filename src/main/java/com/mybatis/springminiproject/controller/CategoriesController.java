package com.mybatis.springminiproject.controller;


import com.mybatis.springminiproject.model.Categories;
import com.mybatis.springminiproject.model.response.ApiResponse;
import com.mybatis.springminiproject.service.CategoriesService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("api/v1/categories")
@AllArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;
    @GetMapping
    public ResponseEntity<?> getAllCategories(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "5") Integer limit) {
        ApiResponse<List<Categories>> response = ApiResponse.<List<Categories>>builder()
                .message("Get all categories successfully.")
                .payload(categoriesService.getAllCategories(offset,limit))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
