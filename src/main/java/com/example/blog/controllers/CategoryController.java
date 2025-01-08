package com.example.blog.controllers;

import com.example.blog.domain.dtos.CategoryDto;
import com.example.blog.domain.entities.CategoryEntity;
import com.example.blog.exceptions.AlreadyInUseException;
import com.example.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    List<CategoryEntity> getAllCategories() {
        return ResponseEntity.ok(categoryService.listCategories()).getBody();
    }

    @PostMapping
    ResponseEntity<?> createCategory(@RequestBody CategoryDto category) {
        try {
            return ResponseEntity.ok(categoryService.createCategory(category));
        } catch (AlreadyInUseException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }
}