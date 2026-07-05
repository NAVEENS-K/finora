package com.naveens.finora.category.service;

import com.naveens.finora.category.dto.request.CreateCategoryRequestDto;
import com.naveens.finora.category.dto.response.CategoryResponseDto;
import com.naveens.finora.category.entity.Category;
import com.naveens.finora.category.mapper.CategoryMapper;
import com.naveens.finora.category.repository.CategoryRepository;
import com.naveens.finora.exception.CategoryAlreadyExistsException;
import com.naveens.finora.exception.CategoryNotFoundException;
import com.naveens.finora.user.entity.User;
import com.naveens.finora.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private User getCurrentUser(){
        return userRepository.findById(1L)
                .orElseThrow();
    }

    public CategoryResponseDto create(CreateCategoryRequestDto request){
        User user = getCurrentUser();

        if(categoryRepository.existsByUserIdAndName(user.getId(), request.getName())){
            throw new CategoryAlreadyExistsException("Category already exists.");
        }
        Category category = categoryMapper.toEntity(request);
        category.setUser(user);

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    public List<CategoryResponseDto> getAllCategories(){
        User user = getCurrentUser();

        List<Category> categories = categoryRepository.findByUserIdOrderByNameAsc(user.getId());

        return categories.stream()
                .map(categoryMapper::toResponse)
                .toList();

    }

    public CategoryResponseDto getCategoryById(Long id){

        User user = getCurrentUser();

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        if(!category.getUser().getId().equals(user.getId())){
            throw new CategoryNotFoundException("Category not found.");
        }
        return categoryMapper.toResponse(category);
    }

    public CategoryResponseDto updateCategory(Long id, CreateCategoryRequestDto request){
        User user = getCurrentUser();

        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Category not found."));
        if(!category.getUser().getId().equals(user.getId())){
            throw new CategoryNotFoundException("Category not found.");
        }
        if(categoryRepository.existsByUserIdAndNameAndIdNot(user.getId(), request.getName(), id)){
            throw new CategoryAlreadyExistsException("Category already exists.");
        }
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.toResponse(updatedCategory);
    }

    public void deleteCategory(Long id){

        User user = getCurrentUser();

        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Category not found"));

        if(!category.getUser().getId().equals(user.getId())){
            throw new CategoryNotFoundException("Category not found.");
        }
        categoryRepository.delete(category);
    }
}
