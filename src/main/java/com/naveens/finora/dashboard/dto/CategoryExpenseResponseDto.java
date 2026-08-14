package com.naveens.finora.dashboard.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryExpenseResponseDto {

    private Long categoryId;

    private String CategoryName;

    private BigDecimal totalSpent;

    private BigDecimal budgetAmount;

    private BigDecimal remainingAmount;

    private BigDecimal budgetUsagePercentage;
}
