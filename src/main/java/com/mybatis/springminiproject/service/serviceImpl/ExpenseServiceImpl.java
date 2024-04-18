package com.mybatis.springminiproject.service.serviceImpl;

import com.mybatis.springminiproject.model.Expenses;
import com.mybatis.springminiproject.model.dto.request.ExpenseRequest;
import com.mybatis.springminiproject.repository.ExpenseRepository;
import com.mybatis.springminiproject.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Override
    public List<Expenses> getAllExpense(int offset, int limit, String column, String select) {
        offset = (offset - 1) * limit;
        return expenseRepository.getAllExpense(offset, limit, column, select);
    }

    @Override
    public Expenses insertExpense(ExpenseRequest expenseRequest, Integer userId) {
        return expenseRepository.insertExpense(expenseRequest,userId);
    }

    @Override
    public Expenses getExpenseById(Integer id) {
        return expenseRepository.getExpenseById(id);
    }

    @Override
    public Expenses updateExpense(Integer id, ExpenseRequest expenseRequest) {
        return expenseRepository.updateExpense(id, expenseRequest);
    }

    @Override
    public Boolean deleteExpense(Integer id) {
        return expenseRepository.deleteExpense(id);
    }
}
