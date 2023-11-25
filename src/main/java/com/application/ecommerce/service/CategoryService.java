package com.application.ecommerce.service;


import com.application.ecommerce.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> listCategories();
    void createCategory(Category category);

    Category readCategory(String categoryName);
    Optional<Category> readCategory(Integer categoryId);
    void updateCategory(Integer categoryID, Category newCategory);

}
