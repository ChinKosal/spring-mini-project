package com.mybatis.springminiproject.controller;

import com.mybatis.springminiproject.model.Expenses;
import com.mybatis.springminiproject.model.dto.request.ExpenseRequest;
import com.mybatis.springminiproject.model.dto.response.ApiResponse;
import com.mybatis.springminiproject.service.ExpenseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerSecurityMarker;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ExpenseController {
    private final ExpenseService expenseService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Expenses>>> getAllExpense(
            @RequestParam(defaultValue = "1") int offset,
            @RequestParam(defaultValue = "3") int limit,
            @RequestParam String column,
            @Parameter(description = "Default value false", schema = @Schema(allowableValues = {"FALSE","TRUE"}))
            @RequestParam(name = "select") Boolean select){
        ApiResponse<List<Expenses>> response = null;
        if(select){
            response = ApiResponse.<List<Expenses>>builder()
                    .message("Show all expense list.")
                    .payload(expenseService.getAllExpense(offset, limit, column, "DESC"))
                    .status(HttpStatus.OK)
                    .dateTime(LocalDateTime.now())
                    .build();
        }else if(!select){
            response = ApiResponse.<List<Expenses>>builder()
                    .message("Show all expense list.")
                    .payload(expenseService.getAllExpense(offset, limit, column, "ASC"))
                    .status(HttpStatus.OK)
                    .dateTime(LocalDateTime.now())
                    .build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<Expenses>> insertExpense(@RequestBody ExpenseRequest expenseRequest){
        ApiResponse<Expenses> response = ApiResponse.<Expenses>builder()
                .message("Insert expense successfully.")
                .payload(expenseService.insertExpense(expenseRequest,1))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Expenses>> getExpenseById(@PathVariable Integer id){
        ApiResponse<Expenses> response = ApiResponse.<Expenses>builder()
                .message("Show detail expense "+id+" by id")
                .payload(expenseService.getExpenseById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Expenses>> updateExpense(@PathVariable Integer id, @RequestBody ExpenseRequest expenseRequest){
        ApiResponse<Expenses> response = ApiResponse.<Expenses>builder()
                .message("Update expense "+id+" successfully")
                .payload(expenseService.updateExpense(id, expenseRequest))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<?>> deleteExpense(@PathVariable Integer id){
        ApiResponse<?> response = ApiResponse.builder()
                .message("Delete expense "+id+" successfully.")
                .payload(null)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

