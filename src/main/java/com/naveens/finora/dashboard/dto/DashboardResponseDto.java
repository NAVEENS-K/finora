package com.naveens.finora.dashboard.dto;


import com.naveens.finora.category.dto.response.CategoryResponseDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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

    private List<CategoryExpenseResponseDto> categoryExpenses;

}
