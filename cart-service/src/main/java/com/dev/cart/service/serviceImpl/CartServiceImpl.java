package com.dev.cart.service.serviceImpl;

import com.dev.cart.service.dto.CartDto;
import com.dev.cart.service.model.Cart;
import com.dev.cart.service.model.CartItem;
import com.dev.cart.service.repository.CartRepository;
import com.dev.cart.service.service.CartService;
import com.dev.common_dto.mapper.DtoEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final DtoEntityMapper<CartDto, Cart> cartDtoMapper;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
        this.cartDtoMapper = DtoEntityMapper.getDtoEntityMapper();
    }

    @Override
    public CartDto createCart(CartDto cartDto) {
        Cart cart = cartDtoMapper.convertFromDtoToEntity(cartDto, Cart.class);
        Cart saved = cartRepository.save(cart);
        return cartDtoMapper.convertFromEntityToDto(saved, CartDto.class);
    }

    @Override
    public CartDto addProductToCart(String userId, String productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        // Sample product data (replace with actual product fetching logic)
        CartItem item = new CartItem(productId, "Sample Product", 100.0, 1);
        cart.addItem(item);

        Cart updated = cartRepository.save(cart);
        return cartDtoMapper.convertFromEntityToDto(updated, CartDto.class);
    }

    @Override
    public CartDto removeProductFromCart(String userId, String productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        Optional<CartItem> itemOpt = cart.getItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst();

        itemOpt.ifPresent(cart::removeItem);

        Cart updated = cartRepository.save(cart);
        return cartDtoMapper.convertFromEntityToDto(updated, CartDto.class);
    }

    @Override
    public CartDto getCartByUserId(String userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));
        return cartDtoMapper.convertFromEntityToDto(cart, CartDto.class);
    }

    @Override
    public List<CartDto> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(cart -> cartDtoMapper.convertFromEntityToDto(cart, CartDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void clearCart(String userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));
        cart.getItems().clear();
        cart.recalculateTotals();
        cartRepository.save(cart);
    }
}
