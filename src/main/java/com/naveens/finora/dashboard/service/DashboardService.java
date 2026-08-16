package com.naveens.finora.dashboard.service;

import com.naveens.finora.budget.entity.Budget;
import com.naveens.finora.budget.repository.BudgetRepository;
import com.naveens.finora.dashboard.dto.CategoryExpenseResponseDto;
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
import java.util.List;
import java.util.Optional;


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

    private List<CategoryExpenseResponseDto> getCategoryExpenseSummary(
            User user,
            LocalDate startDate,
            LocalDate endDate,
            Integer month,
            Integer year
    ){
        List<Object[]> results =
                expenseRepository.getCategoryExpenseSummary(user.getId(), startDate, endDate);

        return results.stream()
                .map(result -> {

                    Long categoryId = (Long) result[0];
                    String categoryName = (String) result[1];
                    BigDecimal totalSpent = (BigDecimal) result[2];

                    Optional<Budget> budget =
                            budgetRepository.findByUserIdAndCategoryIdAndMonthAndYear(
                                    user.getId(),
                                    categoryId,
                                    month,
                                    year
                            );

                    BigDecimal budgetAmount = null;
                    BigDecimal remainingAmount = null;
                    BigDecimal budgetUsagePercentage = null;

                    if(budget.isPresent()){
                        budgetAmount = budget.get().getAmount();

                        remainingAmount = budgetAmount.subtract(totalSpent);

                        if(budgetAmount.compareTo(BigDecimal.ZERO)>0){
                            budgetUsagePercentage = totalSpent.divide(budgetAmount, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
                        }
                    }

                    return CategoryExpenseResponseDto.builder()
                            .categoryId(categoryId)
                            .CategoryName(categoryName)
                            .totalSpent(totalSpent)
                            .budgetAmount(budgetAmount)
                            .remainingAmount(remainingAmount)
                            .budgetUsagePercentage(budgetUsagePercentage)
                            .build();
                        }).toList();
    }

    public DashboardResponseDto getDashboard(Integer month, Integer year){
        User user = getCurrentUser();

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        BigDecimal totalIncome =
                incomeRepository.getTotalIncome(user.getId(), startDate, endDate);

        BigDecimal totalExpense =
                expenseRepository.getTotalExpense(user.getId(), startDate, endDate);

        BigDecimal savings =
                totalIncome.subtract(totalExpense);

        BigDecimal savingsRate = BigDecimal.ZERO;

        if(totalIncome.compareTo(BigDecimal.ZERO) > 0){
            savingsRate = savings.divide(totalIncome, 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        }

        BigDecimal totalBudget = budgetRepository.getTotalBudget(user.getId(), month, year);

        BigDecimal totalBudgetSpent = null;
        BigDecimal remainingBudget = null;

        if(totalBudget.compareTo(BigDecimal.ZERO)> 0){
            totalBudgetSpent = totalExpense;
            remainingBudget = totalBudget.subtract(totalExpense);
        }else{
            totalBudget = null;
        }


        List<CategoryExpenseResponseDto> categoryExpenses = getCategoryExpenseSummary(user, startDate, endDate, month, year);

        return DashboardResponseDto.builder()
                .month(month)
                .year(year)
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .savings(savings)
                .savingsRate(savingsRate)
                .totalBudget(totalBudget)
                .totalBudgetSpent(totalBudgetSpent)
                .remainingBudget(remainingBudget)
                .categoryExpenses(categoryExpenses)
                .build();
    }
}
