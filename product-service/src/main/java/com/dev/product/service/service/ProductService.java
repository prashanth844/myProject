package com.dev.product.service.service;

import java.util.List;

import com.dev.product.service.dto.ProductDto;

public interface ProductService {

	ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(String id);

    ProductDto updateProduct(String id, ProductDto productDto);

    void deleteProduct(String id);
}
