package com.naveens.finora.dashboard.dto;


import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDto {

    private Integer month;
    private Integer year;

    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal savings;
    private BigDecimal savingsRate;

    private BigDecimal totalBudget;
    private BigDecimal totalBudgetSpent;
    private BigDecimal remainingBudget;

}
