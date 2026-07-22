package com.naveens.finora.income.repository;

import com.naveens.finora.income.entity.Income;
import com.naveens.finora.incomeSource.entity.IncomeSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {
Page<Income> findByUserId(Long userId, Pageable pageable);
Optional<Income> findByIdAndUserId(Long id, Long userId);
}
