package com.naveens.finora.budget.service;


import com.naveens.finora.budget.dto.request.CreateBudgetRequestDto;
import com.naveens.finora.budget.dto.response.BudgetResponseDto;
import com.naveens.finora.budget.entity.Budget;
import com.naveens.finora.budget.mapper.BudgetMapper;
import com.naveens.finora.budget.repository.BudgetRepository;
import com.naveens.finora.category.entity.Category;
import com.naveens.finora.category.repository.CategoryRepository;
import com.naveens.finora.exception.BudgetAlreadyExistsException;
import com.naveens.finora.exception.CategoryNotFoundException;
import com.naveens.finora.user.entity.User;
import com.naveens.finora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetMapper budgetMapper;
    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private User getCurrentUser(){
        return userRepository.findById(1L)
                .orElseThrow(()-> new RuntimeException("User not found."));
    }

    public BudgetResponseDto createBudget(CreateBudgetRequestDto request){
        User user = getCurrentUser();

        Category category = categoryRepository.findByIdAndUserId(request.getCategoryId(),user.getId())
                .orElseThrow(()-> new CategoryNotFoundException("Category Not found."));

        boolean exists = budgetRepository.existsByUserIdAndCategoryIdAndMonthAndYear(user.getId(), request.getCategoryId(), request.getMonth(), request.getYear());

        if(exists){
            throw new BudgetAlreadyExistsException("Budget already exists for this category and month.");
        }

        Budget budget = budgetMapper.toEntity(request);
        budget.setCategory(category);
        budget.setUser(user);

        Budget savedBudget = budgetRepository.save(budget);

        return budgetMapper.toResponse(savedBudget);
    }
}
