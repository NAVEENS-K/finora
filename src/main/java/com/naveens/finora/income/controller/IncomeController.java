package com.naveens.finora.income.controller;

import com.naveens.finora.common.response.ApiResponse;
import com.naveens.finora.income.dto.request.CreateIncomeRequestDto;
import com.naveens.finora.income.dto.response.IncomeResponseDto;
import com.naveens.finora.income.service.IncomeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/incomes")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService){
        this.incomeService = incomeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IncomeResponseDto>> createIncome(
            @Valid @RequestBody CreateIncomeRequestDto request
            ){
        IncomeResponseDto income = incomeService.create(request);

        ApiResponse<IncomeResponseDto> response =
                ApiResponse.<IncomeResponseDto>builder()
                        .success(true)
                        .message("income created successfully")
                        .data(income)
                        .build();


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<IncomeResponseDto>>> getPageableIncomes(
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "receivedDate",
                    direction = Sort.Direction.DESC
            )
            Pageable pageable){
        Page<IncomeResponseDto> incomes = incomeService.getAll(pageable);

        ApiResponse<Page<IncomeResponseDto>> response =
                ApiResponse.<Page<IncomeResponseDto>>builder()
                        .success(true)
                        .message("All incomes are retrieved")
                        .data(incomes)
                        .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IncomeResponseDto>> getIncomeById(@PathVariable Long id){
        IncomeResponseDto income = incomeService.getById(id);

        ApiResponse<IncomeResponseDto> response =
        ApiResponse.<IncomeResponseDto>builder()
                .success(true)
                .message("Income retrieved successfully.")
                .data(income)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IncomeResponseDto>> updateIncome(
            @PathVariable Long id,
            @Valid @RequestBody CreateIncomeRequestDto request
    ){
        IncomeResponseDto income = incomeService.updateIncome(id,request);

        ApiResponse<IncomeResponseDto> response =
        ApiResponse.<IncomeResponseDto>builder()
                .success(true)
                .message("Income updated successfully.")
                .data(income)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateIncome(@PathVariable Long id){
        incomeService.deleteIncome(id);

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(true)
                        .build();

        return ResponseEntity.ok(response);
}
}
