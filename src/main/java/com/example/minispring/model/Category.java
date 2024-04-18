package com.example.minispring.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String  categoryId;
    private String categoryName;
    private String categoryDescription;
    private Integer userId;
}
