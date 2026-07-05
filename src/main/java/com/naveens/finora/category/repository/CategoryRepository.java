package com.naveens.finora.category.repository;

import com.naveens.finora.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByUserIdAndName(Long userId, String name);
    List<Category> findByUserId(Long UserId);
}
