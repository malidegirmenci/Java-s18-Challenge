package com.workintech.service;

import com.workintech.converter.DtoConverter;
import com.workintech.dto.CategoryResponse;
import com.workintech.entity.Category;
import com.workintech.exceptions.LibraryException;
import com.workintech.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        throw new LibraryException("Category with given id could not find", HttpStatus.BAD_REQUEST);
    }
    @Override
    public CategoryResponse getById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return DtoConverter.convertToCategoryResponse(optionalCategory.get());
        }
        throw new LibraryException("Category with given id could not find", HttpStatus.BAD_REQUEST);
    }

    @Override
    public CategoryResponse save(Category category) {
        return DtoConverter.convertToCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public List<CategoryResponse> findAll() {
        return DtoConverter.convertToCategoryResponseList(categoryRepository.findAll());
    }

    @Override
    public CategoryResponse delete(Long id) {
        Category willDeleteCategory = findById(id);
        categoryRepository.delete(willDeleteCategory);
        return DtoConverter.convertToCategoryResponse(willDeleteCategory);
    }

    @Override
    public CategoryResponse update(Long id, Category category) {
        Category willUpdateCategory = findById(id);
        willUpdateCategory.setName(category.getName());
        willUpdateCategory.setBooks(category.getBooks());
        return DtoConverter.convertToCategoryResponse(categoryRepository.save(willUpdateCategory));
    }
}
