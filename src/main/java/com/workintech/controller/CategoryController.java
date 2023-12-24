package com.workintech.controller;

import com.workintech.converter.DtoConverter;
import com.workintech.dto.CategoryResponse;
import com.workintech.entity.Category;
import com.workintech.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    @PostMapping
    public CategoryResponse save(@RequestBody Category category){
        return categoryService.save(category);
    }
    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id){
        return categoryService.getById(id);
    }
    @GetMapping
    public List<CategoryResponse> getAll(){
        return categoryService.findAll();
    }
    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody Category category){
        return categoryService.update(id,category);
    }

    @DeleteMapping("/{id}")
    public CategoryResponse delete(@PathVariable Long id){
        return categoryService.delete(id);
    }
}
