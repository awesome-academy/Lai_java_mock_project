package com.example.booking_tour.service;

import org.springframework.stereotype.Service;
import com.example.booking_tour.dto.CategoryCreateRequest;
import com.example.booking_tour.entity.Category;
import com.example.booking_tour.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CategoryCreateRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }
}
