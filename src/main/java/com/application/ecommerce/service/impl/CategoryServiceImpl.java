package com.application.ecommerce.service.impl;

//implementation

import com.application.ecommerce.model.Category;
import com.application.ecommerce.repository.CategoryRepository;
import com.application.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;

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
}
