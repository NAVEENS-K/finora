package com.naveens.finora.category.controller;


import com.naveens.finora.category.dto.request.CreateCategoryRequestDto;
import com.naveens.finora.category.dto.response.CategoryResponseDto;
import com.naveens.finora.category.entity.Category;
import com.naveens.finora.category.service.CategoryService;
import com.naveens.finora.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getAllCategories(){
        List<CategoryResponseDto> categories =
                categoryService.getAllCategories();
        ApiResponse<List<CategoryResponseDto>> response =
                ApiResponse.<List<CategoryResponseDto>>builder()
                        .success(true)
                        .message("all categories retrived.")
                        .data(categories)
                        .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> getCategoryById(@PathVariable Long id){
        CategoryResponseDto category = categoryService.getCategoryById(id);

        ApiResponse<CategoryResponseDto> response =
                ApiResponse.<CategoryResponseDto>builder()
                        .success(true)
                        .message("Category retrived successfully.")
                        .data(category)
                        .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> updateCategory(
            @PathVariable Long id,
            @Valid
            @RequestBody CreateCategoryRequestDto request
    ){
        CategoryResponseDto category = categoryService.updateCategory(id, request);

        ApiResponse<CategoryResponseDto> response =
        ApiResponse.<CategoryResponseDto>builder()
                .success(true)
                .message("Category updated succsefully")
                .data(category)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Category deleted successfully")
                        .data(null)
                        .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
