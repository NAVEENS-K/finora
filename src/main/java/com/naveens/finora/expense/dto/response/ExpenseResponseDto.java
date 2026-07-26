package com.naveens.finora.expense.dto.response;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponseDto {

    private Long id;

    private BigDecimal amount;

    private String description;

    private LocalDate expenseDate;

    private Long categoryId;

    private String categoryName;
}
