package com.example.blog.services.impl;

import com.example.blog.domain.dtos.CategoryDto;
import com.example.blog.domain.entities.CategoryEntity;
import com.example.blog.exceptions.AlreadyInUseException;
import com.example.blog.repositories.CategoryRepository;
import com.example.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public CategoryEntity createCategory(CategoryDto dto) {
        if(categoryRepository.findByName(dto.name()).isPresent()) {
            throw new AlreadyInUseException("Category already exists with name: " + dto.name());
        }

        CategoryEntity category = new CategoryEntity();
        category.setName(dto.name());
        categoryRepository.save(category);
        log.info("Saved Category: " + category);

        return category;
    }


}
