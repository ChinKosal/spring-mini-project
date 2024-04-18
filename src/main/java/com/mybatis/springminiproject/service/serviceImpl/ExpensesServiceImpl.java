package com.mybatis.springminiproject.service.serviceImpl;

import com.mybatis.springminiproject.model.Expenses;
import com.mybatis.springminiproject.model.expenseResuest.ExpensesRequest;
import com.mybatis.springminiproject.repository.ExpensesRepository;
import com.mybatis.springminiproject.service.ExpensesService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {
    private final ExpensesRepository expensesRepository;
    @Override
    public List<Expenses> getAllExpense(Integer offset,Integer limit) {
        offset = (offset-1) * limit;
        return expensesRepository.getAllExpense(offset,limit);
    }

    @Override
    public Expenses getExpenseById(Integer expenseId) {
        return expensesRepository.getExpenseById(expenseId);
    }

    @Override
    public Expenses insertExpense(ExpensesRequest expensesRequest) {
        return expensesRepository.insertExpense(expensesRequest);
    }

    @Override
    public Expenses deleteExpense(Integer expenseId) {
        return expensesRepository.deleteExpense(expenseId);
    }

    @Override
    public Expenses updateExpenseById(ExpensesRequest expensesRequest, Integer expenseId) {
        return expensesRepository.updateExpenseById(expensesRequest,expenseId);
    }

}
