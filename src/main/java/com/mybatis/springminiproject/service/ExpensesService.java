package com.mybatis.springminiproject.service;

import com.mybatis.springminiproject.model.Expenses;
import com.mybatis.springminiproject.model.expenseResuest.ExpensesRequest;

import java.util.List;

public interface ExpensesService {
    List<Expenses> getAllExpense(Integer offset, Integer limit);

    Expenses getExpenseById(Integer expenseId);

    Expenses insertExpense(ExpensesRequest expensesRequest);

    Expenses deleteExpense(Integer expenseId);

    Expenses updateExpenseById(ExpensesRequest expensesRequest, Integer expenseId);
}
