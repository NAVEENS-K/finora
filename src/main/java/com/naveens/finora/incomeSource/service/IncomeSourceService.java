package com.naveens.finora.incomeSource.service;

import com.naveens.finora.exception.IncomeSourceAlreadyExistsException;
import com.naveens.finora.exception.IncomeSourceNotFoundException;
import com.naveens.finora.incomeSource.dto.request.CreateIncomeSourceRequestDto;
import com.naveens.finora.incomeSource.dto.response.IncomeSourceResponseDto;
import com.naveens.finora.incomeSource.entity.IncomeSource;
import com.naveens.finora.incomeSource.mapper.IncomeSourceMapper;
import com.naveens.finora.incomeSource.repository.IncomeSourceRepository;
import com.naveens.finora.user.entity.User;
import com.naveens.finora.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<IncomeSourceResponseDto> getAllIncomeSources(){
        User user = getCurrentUser();

        List<IncomeSource> incomeSources = incomeSourceRepository.findByUserIdOrderByNameAsc(user.getId());

        return incomeSources.stream()
                .map(incomeSourceMapper::toResponse)
                .toList();
    }
    public IncomeSourceResponseDto getIncomeSourceById(Long id){
        User user = getCurrentUser();
        IncomeSource incomeSource = incomeSourceRepository.findById(id)
                .orElseThrow(() -> new IncomeSourceNotFoundException("Income source Not found."));

        if(!incomeSource.getUser().getId().equals(user.getId())){
            throw new IncomeSourceNotFoundException("Income Source not found.");
        }

        return incomeSourceMapper.toResponse(incomeSource);
    }

    public IncomeSourceResponseDto updateIncomeSource(Long id,CreateIncomeSourceRequestDto request){
        User user = getCurrentUser();

        IncomeSource incomeSource = incomeSourceRepository.findById(id)
                .orElseThrow(() -> new IncomeSourceNotFoundException("income source not found."));

        if(!incomeSource.getUser().getId().equals(user.getId())){
            throw new IncomeSourceNotFoundException("income source not found.");
        }

        if(incomeSourceRepository.existsByUserIdAndNameAndIdNot(user.getId(), request.getName(),id)){
            throw new IncomeSourceNotFoundException("income source already exists.");
        }

        incomeSource.setName(request.getName());
        incomeSource.setDescription(request.getDescription());

        IncomeSource updatedIncomeSource = incomeSourceRepository.save(incomeSource);

        return incomeSourceMapper.toResponse(updatedIncomeSource);
    }

    public void deleteIncomeSource(Long id){
        User user = getCurrentUser();

        IncomeSource incomeSource = incomeSourceRepository.findById(id)
                .orElseThrow(()-> new IncomeSourceNotFoundException("income source not found"));

        if(!incomeSource.getUser().getId().equals(user.getId())){
            throw new IncomeSourceNotFoundException("income source not found.");
        }
        incomeSourceRepository.delete(incomeSource);
    }
}
