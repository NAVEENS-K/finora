package com.naveens.finora.exception;

import com.naveens.finora.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleEmailAlreadyExists(
            EmailAlreadyExistsException e){
        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(false)
                        .message(e.getMessage())
                        .errors(null)
                        .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidException(MethodArgumentNotValidException e){

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(false)
                        .message(e.getMessage())
                        .errors(null)
                        .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException e){
        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(false)
                        .message(e.getMessage())
                        .errors(null)
                        .build();
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCategoryNotFoundException(CategoryNotFoundException e){
        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(false)
                        .message(e.getMessage())
                        .errors(null)
                        .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
    @ExceptionHandler(IncomeSourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleIncomeSourceAlreadyExixtsException(IncomeSourceAlreadyExistsException e){
        ApiResponse<Void> response=
                ApiResponse.<Void>builder()
                        .success(false)
                        .message(e.getMessage())
                        .errors(null)
                        .build();
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(IncomeSourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleIncomeSourceNotFoundException(IncomeSourceNotFoundException e){
        ApiResponse<Void> response =
        ApiResponse.<Void>builder()
                .success(false)
                .data(null)
                .message(e.getMessage())
                .errors(null)
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(IncomeNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleIncomeNotFoundException(IncomeNotFoundException e){
        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(false)
                        .message(e.getMessage())
                        .errors(null)
                        .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleExpenseNotFoundException(ExpenseNotFoundException e){
        ApiResponse<Void> response=
        ApiResponse.<Void>builder()
                .success(false)
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);

    }

    @ExceptionHandler(BudgetAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleBudgetAlreadyExistsException(Exception e){
        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .success(false)
                        .message(e.getMessage())
                        .build();
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
 }
