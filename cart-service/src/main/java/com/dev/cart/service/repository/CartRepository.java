package com.dev.cart.service.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.cart.service.model.Cart;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Find a cart by userId, returns Optional to handle "not found" safely
    Optional<Cart> findByUserId(String userId);
}

