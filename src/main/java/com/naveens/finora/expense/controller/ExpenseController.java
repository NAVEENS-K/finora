package com.naveens.finora.expense.controller;


import com.naveens.finora.common.response.ApiResponse;
import com.naveens.finora.expense.dto.request.CreateExpenseRequestDto;
import com.naveens.finora.expense.dto.response.ExpenseResponseDto;
import com.naveens.finora.expense.entity.Expense;
import com.naveens.finora.expense.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
@Setter
@Getter
@RequiredArgsConstructor
//@AllArgsConstructor
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
}
