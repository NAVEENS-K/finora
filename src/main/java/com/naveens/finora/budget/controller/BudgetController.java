package com.naveens.finora.budget.controller;

import com.naveens.finora.budget.dto.request.CreateBudgetRequestDto;
import com.naveens.finora.budget.dto.response.BudgetResponseDto;
import com.naveens.finora.budget.service.BudgetService;
import com.naveens.finora.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<ApiResponse<Page<BudgetResponseDto>>> getAllBudgets(
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "year",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ){
        Page<BudgetResponseDto> budgets = budgetService.getAllBudgets(pageable);

        ApiResponse<Page<BudgetResponseDto>> response =
                ApiResponse.<Page<BudgetResponseDto>>builder()
                        .success(true)
                        .data(budgets)
                        .message("Budgets retrieved successfully")
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<BudgetResponseDto>> getBudgetById(
            @PathVariable Long id
    ){
        BudgetResponseDto budget = budgetService.getBudgetById(id);

        ApiResponse<BudgetResponseDto> response =
        ApiResponse.<BudgetResponseDto>builder()
                .success(true)
                .message("Budget retrieved successfully.")
                .data(budget)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<BudgetResponseDto>> updateBudgetById(
            @PathVariable Long id,
            @Valid @RequestBody CreateBudgetRequestDto request
    ){
        BudgetResponseDto budget = budgetService.updateBudgetById(id, request);

        ApiResponse<BudgetResponseDto> response =
                ApiResponse.<BudgetResponseDto>builder()
                        .success(true)
                        .message("Budget updated successfully.")
                        .data(budget)
                        .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBudgetById(@PathVariable Long id){
        budgetService.deleteBudgetById(id);

        ApiResponse<Void> response =
        ApiResponse.<Void>builder()
                .success(true)
                .message("Budget deleted Successfully.")
                .build();

        return ResponseEntity.ok(response);
    }
}
