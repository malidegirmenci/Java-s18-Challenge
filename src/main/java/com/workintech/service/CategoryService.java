package com.workintech.service;

import com.workintech.dto.CategoryResponse;
import com.workintech.entity.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);
    CategoryResponse getById(Long id);
    CategoryResponse save(Category category);
    List<CategoryResponse> findAll();
    CategoryResponse delete(Long id);
    CategoryResponse update(Long id, Category category);
}
