package com.application.ecommerce.service.impl;

//implementation

import com.application.ecommerce.model.Category;
import com.application.ecommerce.repository.CategoryRepository;
import com.application.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }
    @Override
    public Category readCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
    @Override
    public Optional<Category> readCategory(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }
    @Override
    public void updateCategory(Integer categoryID, Category newCategory) {
        Category category = categoryRepository.findById(categoryID).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        categoryRepository.save(category);
    }
}
