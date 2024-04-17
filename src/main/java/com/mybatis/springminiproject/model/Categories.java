package com.mybatis.springminiproject.model;
import com.mybatis.springminiproject.model.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
    private Integer categoryId;
    private String name;
    private String description;
    private UserResponse user;
}
