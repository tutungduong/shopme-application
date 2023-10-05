package com.application.ecommerce.service.impl;

//implementation

import com.application.ecommerce.model.Category;
import com.application.ecommerce.repository.CategoryRepository;
import com.application.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> listCategory() {
       return categoryRepository.findAll();
    }

    @Override
    public void editCategory(int categoryId, Category updateCategory) {
        Category category = categoryRepository.findById(categoryId).get();
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImageUrl(updateCategory.getImageUrl());
        categoryRepository.save(category);
    }

    @Override
    public boolean findById(int categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }

    @Override
    public Optional<Category> findById(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
