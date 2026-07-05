package com.naveens.finora.category.controller;


import com.naveens.finora.category.dto.request.CreateCategoryRequestDto;
import com.naveens.finora.category.dto.response.CategoryResponseDto;
import com.naveens.finora.category.service.CategoryService;
import com.naveens.finora.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDto>> createCategory(
            @Valid
            @RequestBody CreateCategoryRequestDto request
            ){
        CategoryResponseDto category = categoryService.create((request));

        ApiResponse<CategoryResponseDto> response =
                ApiResponse.<CategoryResponseDto>builder()
                        .success(true)
                        .message("Category created successfully.")
                        .data(category)
                        .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
