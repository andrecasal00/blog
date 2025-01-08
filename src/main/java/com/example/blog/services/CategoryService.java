package com.example.blog.services;

import com.example.blog.domain.dtos.CategoryDto;
import com.example.blog.domain.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> listCategories();
    CategoryEntity createCategory(CategoryDto dto);
}
