package com.application.ecommerce.service;

import com.application.ecommerce.dto.ProductDto;
import com.application.ecommerce.exceptions.ProductNotExistException;
import com.application.ecommerce.model.Category;
import com.application.ecommerce.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    void createProduct(ProductDto productDto, Category category);

    List<ProductDto> getAllProducts();

    void updateProduct(ProductDto productDto, Integer productId) throws Exception;

    Product getProductById(Integer productId)  throws ProductNotExistException;
}
