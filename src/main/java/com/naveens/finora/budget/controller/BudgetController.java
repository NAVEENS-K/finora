package com.naveens.finora.budget.controller;

import com.naveens.finora.budget.dto.request.CreateBudgetRequestDto;
import com.naveens.finora.budget.dto.response.BudgetResponseDto;
import com.naveens.finora.budget.service.BudgetService;
import com.naveens.finora.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @PostMapping
    public ResponseEntity<ApiResponse<BudgetResponseDto>> createBudget(
            @Valid
            @RequestBody
            CreateBudgetRequestDto request
    ){
        BudgetResponseDto budget = budgetService.createBudget(request);

        ApiResponse<BudgetResponseDto> response =
                ApiResponse.<BudgetResponseDto>builder()
                        .success(true)
                        .message("Budget Created Successfully.")
                        .data(budget)
                        .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
