package com.mybatis.springminiproject.model.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesRequest {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String description;
}
