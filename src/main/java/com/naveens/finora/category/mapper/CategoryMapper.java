package com.naveens.finora.category.mapper;

import com.naveens.finora.category.dto.request.CreateCategoryRequestDto;
import com.naveens.finora.category.dto.response.CategoryResponseDto;
import com.naveens.finora.category.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CreateCategoryRequestDto request){
        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return category;
    }

    public CategoryResponseDto toResponse(Category category){
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
