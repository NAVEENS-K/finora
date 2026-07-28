package com.naveens.finora.income.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
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
public class CreateIncomeRequestDto {

    @NotNull
    @Column(nullable = false, precision = 15, scale = 2)
    @DecimalMin(value = "0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Received date is required")
    private LocalDate receivedDate;

    @NotNull(message = "Income source id is required")
    private Long incomeSourceId;
}
