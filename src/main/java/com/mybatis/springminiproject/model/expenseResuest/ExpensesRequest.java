package com.mybatis.springminiproject.model.expenseResuest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesRequest {
    private Integer amount;
    private String description;
    private LocalDateTime dateTime;
    private Integer userId;
    private Integer categoryId;
}
