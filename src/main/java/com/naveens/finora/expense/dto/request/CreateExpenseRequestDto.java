package com.naveens.finora.expense.dto.request;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateExpenseRequestDto {

    @NotNull(message = "amount is required.")
    @DecimalMin(value = "0", inclusive = false, message = "amount must be greater than 0.")
    private BigDecimal amount;

    @Size(max = 255, message = "description cannot exceed 255 characters.")
    private String description;

    @NotNull(message = "Expense date is required")
    private LocalDate expenseDate;

    private Long categoryId;


}
