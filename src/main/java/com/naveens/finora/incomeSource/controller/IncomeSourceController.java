package com.naveens.finora.incomeSource.controller;


import com.naveens.finora.common.response.ApiResponse;
import com.naveens.finora.incomeSource.dto.request.CreateIncomeSourceRequestDto;
import com.naveens.finora.incomeSource.dto.response.IncomeSourceResponseDto;
import com.naveens.finora.incomeSource.service.IncomeSourceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/income_sources")
public class IncomeSourceController {
    private final IncomeSourceService incomeSourceService;

    public IncomeSourceController(IncomeSourceService incomeSourceService){
        this.incomeSourceService = incomeSourceService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IncomeSourceResponseDto>> createIncomeSource(
            @Valid
            @RequestBody CreateIncomeSourceRequestDto request
            ){

        IncomeSourceResponseDto incomeSource = incomeSourceService.create(request);

        ApiResponse<IncomeSourceResponseDto> response=
        ApiResponse.<IncomeSourceResponseDto>builder()
                .success(true)
                .message("Income created successfully.")
                .data(incomeSource)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
