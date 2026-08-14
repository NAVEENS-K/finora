package com.naveens.finora.dashboard.service;

import com.naveens.finora.budget.repository.BudgetRepository;
import com.naveens.finora.dashboard.dto.DashboardResponseDto;
import com.naveens.finora.expense.repository.ExpenseRepository;
import com.naveens.finora.income.repository.IncomeRepository;
import com.naveens.finora.user.entity.User;
import com.naveens.finora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;
    private final IncomeRepository incomeRepository;
    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;

    private User getCurrentUser(){
        return userRepository.findById(1L)
                .orElseThrow(()-> new RuntimeException("User not found."));
    }

    public DashboardResponseDto getDashboard(Integer month, Integer year){
        User user = getCurrentUser();

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        BigDecimal totalIncome =
                incomeRepository.getTotalIncome(user.getId(), startDate, endDate);

        BigDecimal totalExpense =
                expenseRepository.getTotalExpense(user.getId(), startDate, endDate);

        BigDecimal totalBudget =
                budgetRepository.getTotalBudget(user.getId(), month, year);

        BigDecimal savings =
                totalIncome.subtract(totalExpense);

        BigDecimal savingsRate = BigDecimal.ZERO;

        if(totalIncome.compareTo(BigDecimal.ZERO) > 0){
            savingsRate = savingsRate.divide(totalIncome, 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        }

        BigDecimal remainingBudget =
                totalBudget.subtract(totalExpense);

        return DashboardResponseDto.builder()
                .month(month)
                .year(year)
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .savings(savings)
                .savingsRate(savingsRate)
                .totalBudget(totalBudget)
                .totalBudgetSpent(totalExpense)
                .remainingBudget(remainingBudget)
                .build();
    }
}
