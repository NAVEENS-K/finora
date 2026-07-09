package com.naveens.finora.incomeSource.service;

import com.naveens.finora.category.entity.Category;
import com.naveens.finora.exception.IncomeSourceAlreadyExistsException;
import com.naveens.finora.incomeSource.dto.request.CreateIncomeSourceRequestDto;
import com.naveens.finora.incomeSource.dto.response.IncomeSourceResponseDto;
import com.naveens.finora.incomeSource.entity.IncomeSource;
import com.naveens.finora.incomeSource.mapper.IncomeSourceMapper;
import com.naveens.finora.incomeSource.repository.IncomeSourceRepository;
import com.naveens.finora.user.entity.User;
import com.naveens.finora.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class IncomeSourceService {
    private final IncomeSourceRepository incomeSourceRepository;
    private final IncomeSourceMapper incomeSourceMapper;
    private final UserRepository userRepository;

    public IncomeSourceService(IncomeSourceRepository incomeSourceRepository, IncomeSourceMapper incomeSourceMapper, UserRepository userRepository){

        this.incomeSourceRepository = incomeSourceRepository;
        this.incomeSourceMapper = incomeSourceMapper;
        this.userRepository = userRepository;
    }

    private User getCurrentUser(){
        return userRepository.findById(1L)
                .orElseThrow();
    }

    public IncomeSourceResponseDto create(CreateIncomeSourceRequestDto request){
        User user = getCurrentUser();

        if(incomeSourceRepository.existsByUserIdAndName(user.getId(), request.getName())){
            throw new IncomeSourceAlreadyExistsException("income source already exists.");
        }
        IncomeSource incomeSource = incomeSourceMapper.toEntity(request);
        incomeSource.setUser(user);

        IncomeSource savedIncomeSource = incomeSourceRepository.save(incomeSource);
        return incomeSourceMapper.toResponse(savedIncomeSource);
    }
}
