package com.application.ecommerce.controller;

import com.application.ecommerce.common.ApiResponse;
import com.application.ecommerce.dto.ProductDto;
import com.application.ecommerce.model.Category;
import com.application.ecommerce.service.CategoryService;
import com.application.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
        Optional<Category> optionalCategory = categoryService.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"category does not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true,"product has been added"),HttpStatus.CREATED);
    }
    // list all the products
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
      List<ProductDto> products = productService.getAllProducts();
      return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // update a product
    @PostMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID,
                                                     @RequestBody @Valid ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryService.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, productID);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }
}
