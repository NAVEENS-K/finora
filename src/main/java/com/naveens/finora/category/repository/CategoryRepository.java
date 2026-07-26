package com.naveens.finora.category.repository;

import com.naveens.finora.category.entity.Category;
import com.naveens.finora.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByUserIdAndName(Long userId, String name);
    List<Category> findByUserIdOrderByNameAsc(Long UserId);
    boolean existsByUserIdAndNameAndIdNot(Long userId, String name, Long id);
    Optional<Category> findByUserIdAndName(Long userId, String name);
    Optional<Category> findByIdAndUserId(Long id, Long UserId);

}
