package com.example.minispring.model.Response;

import com.example.minispring.model.Response.AppUserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;
}
