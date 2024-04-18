package com.mybatis.springminiproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expenses {
    private Integer expenseId;
    private Double amount;
    private String description;
    private LocalDateTime date;
    private Integer userId;
    private Integer categoryId;
}
