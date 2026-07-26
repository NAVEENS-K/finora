package com.naveens.finora.expense.mapper;


import com.naveens.finora.expense.dto.request.CreateExpenseRequestDto;
import com.naveens.finora.expense.dto.response.ExpenseResponseDto;
import com.naveens.finora.expense.entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public Expense toEntity(CreateExpenseRequestDto request){
        Expense expense = new Expense();

        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setExpenseDate(request.getExpenseDate());

        return expense;
    }

    public ExpenseResponseDto toResponse(Expense expense){
        return ExpenseResponseDto.builder()
                .id(expense.getId())
                .categoryId(expense.getCategory().getId())
                .categoryName(expense.getCategory().getName())
                .description(expense.getDescription())
                .expenseDate(expense.getExpenseDate())
                .amount(expense.getAmount())
                .build();

    }
}
