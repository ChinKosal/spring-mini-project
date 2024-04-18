package com.example.minispring.service.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.minispring.controller.AuthController;
import com.example.minispring.model.AppUser;
import com.example.minispring.model.Expense;
import com.example.minispring.model.Request.ExpenseRequest;
import com.example.minispring.repository.AppUserRepository;
import com.example.minispring.repository.ExpenseRepository;
import com.example.minispring.service.ExpenseService;
@Service
public class ExpenseServiceImp implements ExpenseService{
    private ExpenseRepository expenseRepository;
    private AppUserRepository appUserRepository;
    public ExpenseServiceImp(ExpenseRepository expenseRepository,AppUserRepository appUserRepository) {
        this.expenseRepository = expenseRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public List<Expense> getAllExpense() {
        String email = AuthController.getUsernameOfCurrentUser();
        AppUser userId = appUserRepository.findByEmail(email);
        List<Expense> expense = expenseRepository.getAllExpense(userId.getId());
        if(expense == null){
            return null;
        }
        return expense;
    }

    @Override
    public Expense getExpenseById(Integer expenseId){
        String email = AuthController.getUsernameOfCurrentUser();
        AppUser userId = appUserRepository.findByEmail(email);
        Expense expense = expenseRepository.getExpenseById(expenseId,userId.getId());
        if(expense == null){
            return null;
        }
        return expense;
    }

    @Override
    public Expense addNewExpense(ExpenseRequest expenseRequest){
        String email = AuthController.getUsernameOfCurrentUser();
        AppUser userId = appUserRepository.findByEmail(email);
        Expense expense = expenseRepository.addNewExpense(expenseRequest,userId.getId());
        if(expense == null){
            return null;
        }
        return expense;
    }

    @Override
    public Expense updateExpense(ExpenseRequest expenseRequest,Integer id){
        String email = AuthController.getUsernameOfCurrentUser();
        AppUser userId = appUserRepository.findByEmail(email);
        Expense expense = expenseRepository.updateExpense(expenseRequest,id,userId.getId());
        if(expense == null){
            return null;
        }
        return expense;
    }

    @Override
    public Expense deleteExpense(Integer expenseId){
        String email = AuthController.getUsernameOfCurrentUser();
        AppUser userId = appUserRepository.findByEmail(email);
        System.out.println(expenseId);
        System.out.println(userId.getId());
        Expense expense = expenseRepository.deleteExpense(expenseId,userId.getId());
        if(expense == null){
            return null;
        }
        return expense;
    }
}
