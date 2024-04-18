package com.example.minispring.model.Request;

import java.time.LocalDateTime;

import com.example.minispring.model.AppUser;
import com.example.minispring.model.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseRequest {
    private double amount;
    private String description;
    private LocalDateTime date;
    private Integer categoryId;
}
