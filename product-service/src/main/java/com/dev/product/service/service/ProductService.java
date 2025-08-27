package com.dev.product.service.service;

import java.util.List;

import com.dev.product.service.dto.ProductDto;

public interface ProductService {

	ProductDto createProduct(ProductDto productDto);

    // Get all products
    List<ProductDto> getAllProducts();

    // Get a product by its ID
    ProductDto getProductById(String id);

    // Update a product by its ID
    ProductDto updateProduct(String id, ProductDto productDto);

    // Delete a product by its ID
    void deleteProduct(String id);
}
