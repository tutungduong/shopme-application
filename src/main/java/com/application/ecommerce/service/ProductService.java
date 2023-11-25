package com.application.ecommerce.service;

import com.application.ecommerce.dto.product.ProductDto;
import com.application.ecommerce.exceptions.ProductNotExistException;
import com.application.ecommerce.model.Category;
import com.application.ecommerce.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> listProducts();
    void addProduct(ProductDto productDto, Category category);
    void updateProduct(Integer productID, ProductDto productDto, Category category);

    Product getProductById(Integer productId) throws ProductNotExistException;
}
