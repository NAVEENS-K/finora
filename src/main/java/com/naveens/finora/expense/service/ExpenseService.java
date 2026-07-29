package com.naveens.finora.expense.service;


import com.naveens.finora.category.dto.request.CreateCategoryRequestDto;
import com.naveens.finora.category.entity.Category;
import com.naveens.finora.category.repository.CategoryRepository;
import com.naveens.finora.category.service.CategoryService;
import com.naveens.finora.exception.CategoryNotFoundException;
import com.naveens.finora.expense.dto.request.CreateExpenseRequestDto;
import com.naveens.finora.expense.dto.response.ExpenseResponseDto;
import com.naveens.finora.expense.entity.Expense;
import com.naveens.finora.expense.mapper.ExpenseMapper;
import com.naveens.finora.expense.repository.ExpenseRepository;
import com.naveens.finora.user.entity.User;
import com.naveens.finora.user.repository.UserRepository;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
//@AllArgsConstructor
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    private User getCurrentUser(){
        return userRepository.findById(1L)
                .orElseThrow(()-> new RuntimeException("User not found."));
    }

    public Category resolveCategory(User user, Long categoryId){
        if(categoryId!=null){
            return categoryRepository.findByIdAndUserId(categoryId, user.getId())
                    .orElseThrow(()-> new CategoryNotFoundException("Category not found."));
        }
        return categoryRepository.findByUserIdAndName(user.getId(), "Uncategorized")
                .orElseGet(()-> {
                    Category category = new Category();
                    category.setName("Uncategorized");
                    category.setUser(user);

                    return categoryRepository.save(category);
                });
    }

    public ExpenseResponseDto createExpense(CreateExpenseRequestDto request){
        User user = getCurrentUser();

        Category category = resolveCategory(user, request.getCategoryId());

        Expense expense = expenseMapper.toEntity(request);

        expense.setUser(user);
        expense.setCategory(category);

        Expense savedExpense = expenseRepository.save(expense);

        return expenseMapper.toResponse(savedExpense);
    }

    public Page<ExpenseResponseDto> getAllExpenses(Pageable pageable){
        User user = getCurrentUser();

        Page<Expense> expenses =
                expenseRepository.findByUserId(user.getId(), pageable);

        return expenses.map(expenseMapper::toResponse);
    }
}
