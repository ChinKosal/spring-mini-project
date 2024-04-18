package com.example.minispring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.minispring.model.Expense;
import com.example.minispring.model.ApiResponse.ApiResponse;
import com.example.minispring.model.Request.ExpenseRequest;
import com.example.minispring.service.ExpenseService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/expenses")
@SecurityRequirement(name="bearerAuth")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Expense>>> getAllExpense() {
        List<Expense> expenses = expenseService.getAllExpense();
        ApiResponse<List<Expense>> response = ApiResponse.<List<Expense>>builder()
            .message(expenses!=null?"Get All Categories Successfully":"No Data Available")
            .payload(expenses)
            .httpStatus(HttpStatus.OK)
            .localDateTime(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Expense>> getExpenseById(@PathVariable Integer id){
        Expense expense = expenseService.getExpenseById(id);
        ApiResponse<Expense> response = ApiResponse.<Expense>builder()
            .message(expense!=null?"Get All Categories Successfully":"No Data Available")
            .payload(expense)
            .httpStatus(HttpStatus.OK)
            .localDateTime(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Expense>> addNewExpense(@RequestBody ExpenseRequest expenseRequest){
        Expense expense = expenseService.addNewExpense(expenseRequest);
        ApiResponse<Expense> response = ApiResponse.<Expense>builder()
            .message(expense!=null?"Get All Categories Successfully":"No Data Available")
            .payload(expense)
            .httpStatus(HttpStatus.OK)
            .localDateTime(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Expense>> updateExpenseById(@RequestBody ExpenseRequest expenseRequest,@PathVariable Integer id){
        Expense expense = expenseService.updateExpense(expenseRequest,id);
        ApiResponse<Expense> response = ApiResponse.<Expense>builder()
            .message(expense!=null?"Get All Categories Successfully":"No Data Available")
            .payload(expense)
            .httpStatus(HttpStatus.OK)
            .localDateTime(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Expense>> deleteExpense(@PathVariable Integer id){
        Expense expense = expenseService.deleteExpense(id);
        ApiResponse<Expense> response = ApiResponse.<Expense>builder()
            .message(expense!=null?"Get All Categories Successfully":"No Data Available")
            .payload(expense)
            .httpStatus(HttpStatus.OK)
            .localDateTime(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
