package com.naveens.finora.expense.repository;

import com.naveens.finora.expense.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Page<Expense> findByUserId(Long userId ,Pageable pageable);

    Optional<Expense> findByIdAndUserId(Long id, Long userId);


}
