package com.application.ecommerce.service;


import com.application.ecommerce.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void createCategory(Category category);
    List<Category> listCategory();

    void editCategory(int categoryId, Category category);

    boolean findById(int categoryId);
    Optional<Category> findById(Integer categoryId);
}
