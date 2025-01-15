package com.example.blog.controllers;

import com.example.blog.domain.dtos.CategoryDto;
import com.example.blog.domain.entities.CategoryEntity;
import com.example.blog.exceptions.AlreadyInUseException;
import com.example.blog.exceptions.ApiErrorResponse;
import com.example.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
            ApiErrorResponse error = ApiErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(ex.getMessage())
                    .build();
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCategory(@PathVariable UUID id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Category deleted with success!");
        } catch (IllegalStateException ex) {
            ApiErrorResponse error = ApiErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(ex.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
}
