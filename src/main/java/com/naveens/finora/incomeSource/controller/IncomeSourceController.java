package com.naveens.finora.incomeSource.controller;


import com.naveens.finora.category.dto.response.CategoryResponseDto;
import com.naveens.finora.common.response.ApiResponse;
import com.naveens.finora.incomeSource.dto.request.CreateIncomeSourceRequestDto;
import com.naveens.finora.incomeSource.dto.response.IncomeSourceResponseDto;
import com.naveens.finora.incomeSource.service.IncomeSourceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<IncomeSourceResponseDto>>> getAllIncomeSources(){
        List<IncomeSourceResponseDto> incomeSource =
                incomeSourceService.getAllIncomeSources();
        ApiResponse<List<IncomeSourceResponseDto>> response =
        ApiResponse.<List<IncomeSourceResponseDto>>builder()
                .success(true)
                .message("all income Sources are retrived.")
                .data(incomeSource)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IncomeSourceResponseDto>> getIncomeSourceById(@PathVariable Long id){
        IncomeSourceResponseDto incomeSource = incomeSourceService.getIncomeSourceById(id);

        ApiResponse<IncomeSourceResponseDto> response =
                ApiResponse.<IncomeSourceResponseDto>builder()
                        .success(true)
                        .message("income source retrived sucessfully")
                        .data(incomeSource)
                        .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IncomeSourceResponseDto>> updateIncomeSource(

            @PathVariable
            Long id,
            @Valid
            @RequestBody
            CreateIncomeSourceRequestDto request
    ) {
        IncomeSourceResponseDto incomeSource = incomeSourceService.updateIncomeSource(id, request);

        ApiResponse<IncomeSourceResponseDto> response =
        ApiResponse.<IncomeSourceResponseDto>builder()
                .success(true)
                .message("income Source updated sucessfully.")
                .data(incomeSource)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteincomeSource(@PathVariable Long id){
        incomeSourceService.deleteIncomeSource(id);

        ApiResponse<Void> response =
        ApiResponse.<Void>builder()
                .success(true)
                .message("Income source deleted successfully.")
                .data(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
