package com.naveens.finora.category.service;

import com.naveens.finora.category.dto.request.CreateCategoryRequestDto;
import com.naveens.finora.category.dto.response.CategoryResponseDto;
import com.naveens.finora.category.entity.Category;
import com.naveens.finora.category.mapper.CategoryMapper;
import com.naveens.finora.category.repository.CategoryRepository;
import com.naveens.finora.exception.CategoryAlreadyExistsException;
import com.naveens.finora.user.entity.User;
import com.naveens.finora.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, UserRepository userRepository){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.userRepository = userRepository;

    }

    public CategoryResponseDto create(CreateCategoryRequestDto request){
        User user = userRepository.findById(1L)
                .orElseThrow();

        if(categoryRepository.existsByUserIdAndName(user.getId(), request.getName())){
            throw new CategoryAlreadyExistsException("Category already exists.");
        }
        Category category = categoryMapper.toEntity(request);
        category.setUser(user);

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

}
