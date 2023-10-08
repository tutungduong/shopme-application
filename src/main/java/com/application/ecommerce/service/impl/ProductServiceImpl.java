package com.application.ecommerce.service.impl;

import com.application.ecommerce.dto.ProductDto;
import com.application.ecommerce.exceptions.ProductNotExistException;
import com.application.ecommerce.model.Category;
import com.application.ecommerce.model.Product;
import com.application.ecommerce.repository.ProductRepository;
import com.application.ecommerce.service.ProductService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageUrl());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;
    }
    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : allProducts){
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    @Override
    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
     Optional<Product> optionalProduct = productRepository.findById(productId);
     // throw an exception if product does not exists
        if(!optionalProduct.isPresent()){
            throw new Exception("product not present");
        }
        Product product = optionalProduct.get();
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setId(productDto.getId());
        productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer productId) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotExistException("Product id is invalid " + productId);
        return optionalProduct.get();
    }
}
