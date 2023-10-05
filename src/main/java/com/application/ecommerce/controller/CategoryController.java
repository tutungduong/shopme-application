package com.application.ecommerce.controller;


import com.application.ecommerce.common.ApiResponse;
import com.application.ecommerce.model.Category;
import com.application.ecommerce.service.CategoryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse> (new ApiResponse(true,"a new category created"), HttpStatus.CREATED);
    }
    @GetMapping("/")
    public List<Category> createCategory(){
        return categoryService.listCategory();
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category ){
        System.out.println("Category ID " + categoryId);
        // Check to see if the category exists.
        if (!categoryService.findById(categoryId)){
            // If the category doesn't exist then return a response of unsuccessful.
            return new ResponseEntity<ApiResponse>(new ApiResponse(false,"category does not exists"), HttpStatus.NOT_FOUND);
        }
        // If the category exists then update it.
        categoryService.editCategory(categoryId,category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"category has been update"), HttpStatus.OK);
    }
}
