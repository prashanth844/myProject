package com.dev.cart.service.service;

import java.util.List;
import com.dev.cart.service.dto.CartDto;

public interface CartService {

	CartDto createCart(CartDto cartDto);

    CartDto addProductToCart(String userId, String productId);

    CartDto removeProductFromCart(String userId, String productId);

    CartDto getCartByUserId(String userId);

    List<CartDto> getAllCarts();

    void clearCart(String userId);
}
