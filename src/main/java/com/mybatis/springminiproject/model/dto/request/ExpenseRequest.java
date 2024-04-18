package com.mybatis.springminiproject.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {
    private Double amount;
    private String description;
    private LocalDateTime date;
    private Integer categoryId;
}
