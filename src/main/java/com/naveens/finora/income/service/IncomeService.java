package com.naveens.finora.income.service;


import com.naveens.finora.exception.IncomeSourceNotFoundException;
import com.naveens.finora.income.dto.request.CreateIncomeRequestDto;
import com.naveens.finora.income.dto.response.IncomeResponseDto;
import com.naveens.finora.income.entity.Income;
import com.naveens.finora.income.mapper.IncomeMapper;
import com.naveens.finora.income.repository.IncomeRepository;
import com.naveens.finora.incomeSource.entity.IncomeSource;
import com.naveens.finora.incomeSource.repository.IncomeSourceRepository;
import com.naveens.finora.user.entity.User;
import com.naveens.finora.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;
    private final UserRepository userRepository;
    private final IncomeSourceRepository incomeSourceRepository;

    public IncomeService(IncomeSourceRepository incomeSourceRepository, IncomeRepository incomeRepository, UserRepository userRepository, IncomeMapper incomeMapper){
        this.incomeMapper = incomeMapper;
        this.incomeRepository = incomeRepository;
        this.incomeSourceRepository = incomeSourceRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser(){
        return userRepository.findById(1L)
                .orElseThrow();
    }

    @Transactional
    public IncomeResponseDto create(CreateIncomeRequestDto request){

        User user = getCurrentUser();

        IncomeSource incomeSource = incomeSourceRepository.findByIdAndUserId(request.getIncomeSourceId(), user.getId())
                .orElseThrow(()-> new IncomeSourceNotFoundException("Income source not found"));

        Income income = incomeMapper.toEntity(request);

        income.setUser(user);
        income.setIncomeSource(incomeSource);

        Income savedIncome = incomeRepository.save(income);

        return incomeMapper.toResponse(savedIncome);
    }

    public Page<IncomeResponseDto> getAll(Pageable pageable){
        User user = getCurrentUser();

        Page<Income> incomes = incomeRepository.findByUserId(user.getId(), pageable);

        return incomes.map(incomeMapper::toResponse);
    }
}
