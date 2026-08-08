package com.naveens.finora.budget.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CreateBudgetRequestDto {

    @NotNull(message = "Amount is Required")
    @DecimalMin(value = "0", inclusive = false, message = "Amount must be greater than 0.")
    private BigDecimal amount;

    @NotNull(message = "Month is Required")
    @Min(value = 1, message = "Month must be between 1 to 12.")
    @Max(value = 12, message = "Month must be between 1 to 12.")
    private Integer month;

    @NotNull(message = "Year is Required")
    @Min(value = 1, message = "year must be greater than 0.")
    private Integer year;

    @NotNull(message = "Category id is Required")
    private Long categoryId;
}
