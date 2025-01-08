package com.example.blog.services;

import com.example.blog.domain.dtos.CategoryDto;
import com.example.blog.domain.entities.CategoryEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryEntity> listCategories();
    CategoryEntity createCategory(CategoryDto dto);
    void deleteCategory(UUID id);
}
