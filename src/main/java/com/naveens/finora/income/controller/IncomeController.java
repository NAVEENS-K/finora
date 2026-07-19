package com.naveens.finora.income.controller;

import com.naveens.finora.common.response.ApiResponse;
import com.naveens.finora.income.dto.request.CreateIncomeRequestDto;
import com.naveens.finora.income.dto.response.IncomeResponseDto;
import com.naveens.finora.income.service.IncomeService;
import jakarta.validation.Valid;
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
}
