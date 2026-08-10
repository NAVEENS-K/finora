package com.naveens.finora.budget.repository;

import com.naveens.finora.budget.entity.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Page<Budget> findByUserId(Long userId, Pageable pageable);

    Optional<Budget> findByIdAndUserId(Long id, Long userId);

    boolean existsByUserIdAndCategoryIdAndMonthAndYear(Long userId, Long categoryId, Integer month, Integer year);

    boolean existsByUserIdAndCategoryIdAndMonthAndYearAndIdNot(Long userId, Long CategoryId, Integer month, Integer year, Long id);
}
