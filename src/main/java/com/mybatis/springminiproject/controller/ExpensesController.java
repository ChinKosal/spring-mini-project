package com.mybatis.springminiproject.controller;

import com.mybatis.springminiproject.model.Expenses;
import com.mybatis.springminiproject.model.expenseResuest.ExpensesRequest;
import com.mybatis.springminiproject.model.responseApi.APIResponses;
import com.mybatis.springminiproject.service.ExpensesService;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/expense")
@Data
@AllArgsConstructor
public class ExpensesController {
    private final ExpensesService expensesService;
    @GetMapping("/getAll")
    public ResponseEntity<APIResponses<List<Expenses>>> getAllExpense(
            @RequestParam (defaultValue = "1")  Integer offset,
            @RequestParam (defaultValue = "3")  Integer limit
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                new APIResponses<>(
                        "Get all expenses successful",
                        expensesService.getAllExpense(offset,limit),
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }

    @GetMapping("/getById/{expenseId}")
    public ResponseEntity<APIResponses<Expenses>> getExpenseById(@PathVariable Integer expenseId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new APIResponses<>(
                        "get expense by id successful",
                        expensesService.getExpenseById(expenseId),
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }


    @PostMapping("/insert")
    public ResponseEntity<APIResponses<Expenses>> insertExpense(@RequestBody ExpensesRequest expensesRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new APIResponses<>(
                        "Create extense successful",
                        expensesService.insertExpense(expensesRequest),
                        HttpStatus.CREATED,
                        LocalDateTime.now()
                )
        );
    }

    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<APIResponses<Expenses>> deleteExpense(@PathVariable Integer expenseId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new APIResponses<>(
                        "Delete expenses successful",
                        expensesService.deleteExpense(expenseId),
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }

    @PutMapping("/update/{expenseId}")
    public ResponseEntity<APIResponses<Expenses>> updateExpenseById(
            @RequestBody ExpensesRequest expensesRequest,
            @PathVariable Integer expenseId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new APIResponses<>(
                        "Update expense successful",
                        expensesService.updateExpenseById(expensesRequest,expenseId),
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }

}
