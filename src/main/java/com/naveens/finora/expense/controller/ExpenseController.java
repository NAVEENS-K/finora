package com.naveens.finora.expense.controller;


import com.naveens.finora.common.response.ApiResponse;
import com.naveens.finora.expense.dto.request.CreateExpenseRequestDto;
import com.naveens.finora.expense.dto.response.ExpenseResponseDto;
import com.naveens.finora.expense.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseResponseDto>> create(
            @Valid
            @RequestBody
            CreateExpenseRequestDto request){
        ExpenseResponseDto expense = expenseService.createExpense(request);

        ApiResponse<ExpenseResponseDto> response =
                ApiResponse.<ExpenseResponseDto>builder()
                        .success(true)
                        .message("Expense created successfully.")
                        .data(expense)
                        .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ExpenseResponseDto>>> getPageableExpense(
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "expenseDate",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ){
        Page<ExpenseResponseDto> expenses = expenseService.getAllExpenses(pageable);

        ApiResponse<Page<ExpenseResponseDto>> response =
        ApiResponse.<Page<ExpenseResponseDto>>builder()
                .success(true)
                .message("Expenses are retrieved.")
                .data(expenses)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponseDto>> getExpenseById(
            @PathVariable Long id
    ){
        ExpenseResponseDto expense = expenseService.getExpenseById(id);

        ApiResponse<ExpenseResponseDto> response =
                ApiResponse.<ExpenseResponseDto>builder()
                        .success(true)
                        .message("Expense retrieved successfully")
                        .data(expense)
                        .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponseDto>> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody CreateExpenseRequestDto request
    ){
        ExpenseResponseDto expense = expenseService.updateExpense(id, request);

        ApiResponse<ExpenseResponseDto> response =
        ApiResponse.<ExpenseResponseDto>builder()
                .success(true)
                .message("Expense updated successfully.")
                .data(expense)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);

        ApiResponse<Void> response=
        ApiResponse.<Void>builder()
                .success(true)
                .message("Expense deleted successfully.")
                .build();

        return ResponseEntity.ok(response);
    }
}
