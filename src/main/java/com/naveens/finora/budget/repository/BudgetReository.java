package com.naveens.finora.budget.repository;

import com.naveens.finora.budget.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

interface BudgetRepository extends JpaRepository<Budget, Long> {

    boolean existsUserIdAndCategoryIdAndMonthAndYear(Long userId, Long categoryId, Integer month, Integer year);
}
