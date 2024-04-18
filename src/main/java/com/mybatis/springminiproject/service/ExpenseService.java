package com.mybatis.springminiproject.service;


import com.mybatis.springminiproject.model.Expenses;
import com.mybatis.springminiproject.model.dto.request.ExpenseRequest;

import java.util.List;

public interface ExpenseService {
    List<Expenses> getAllExpense(int offset, int limit, String column, String select);

    Expenses insertExpense(ExpenseRequest expenseRequest, Integer userId);

    Expenses getExpenseById(Integer id);

    Expenses updateExpense(Integer id, ExpenseRequest expenseRequest);

    Boolean deleteExpense(Integer id);
}
