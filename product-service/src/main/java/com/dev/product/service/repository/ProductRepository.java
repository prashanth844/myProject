package com.dev.product.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dev.product.service.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // No need to declare save() or other basic CRUD methods
}
