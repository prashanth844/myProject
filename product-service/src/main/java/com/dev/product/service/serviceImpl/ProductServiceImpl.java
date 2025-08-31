package com.dev.product.service.serviceImpl;

import com.dev.product.service.dto.ProductDto;
import com.dev.product.service.exception.ProductNotFoundException;
import com.dev.product.service.model.Product;
import com.dev.product.service.repository.ProductRepository;
import com.dev.product.service.service.ProductService;
import com.dev.common_dto.mapper.DtoEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	private final DtoEntityMapper<ProductDto, Product> mapper = DtoEntityMapper.getDtoEntityMapper();

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product product = mapper.convertFromDtoToEntity(productDto, Product.class);
		Product saved = productRepository.save(product);
		return mapper.convertFromEntityToDto(saved, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		return productRepository.findAll().stream()
				.map(product -> mapper.convertFromEntityToDto(product, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductById(String id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
		return mapper.convertFromEntityToDto(product, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(String id, ProductDto productDto) {
		productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

		Product updatedProduct = mapper.convertFromDtoToEntity(productDto, Product.class);
		updatedProduct.setId(id);

		Product savedProduct = productRepository.save(updatedProduct);
		return mapper.convertFromEntityToDto(savedProduct, ProductDto.class);
	}

	@Override
	public void deleteProduct(String id) {
		if (!productRepository.existsById(id)) {
			throw new ProductNotFoundException("Product not found with id: " + id);
		}
		productRepository.deleteById(id);
	}
}
