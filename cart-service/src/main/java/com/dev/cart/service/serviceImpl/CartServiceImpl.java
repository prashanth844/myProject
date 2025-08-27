package com.dev.cart.service.serviceImpl;

import com.dev.cart.service.dto.CartDto;
import com.dev.cart.service.dto.CartItemDto;
import com.dev.cart.service.exception.CartNotFoundException;
import com.dev.cart.service.model.Cart;
import com.dev.cart.service.model.CartItem;
import com.dev.cart.service.repository.CartRepository;
import com.dev.cart.service.service.CartService;
import com.dev.common_dto.mapper.DtoEntityMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    
    private final DtoEntityMapper<CartDto, Cart> cartMapper;
    
    private final DtoEntityMapper<CartItemDto, CartItem> cartItemMapper;

    public CartServiceImpl(CartRepository cartRepository,
                           DtoEntityMapper<CartDto, Cart> cartMapper,
                           DtoEntityMapper<CartItemDto, CartItem> cartItemMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public CartDto createCart(CartDto cartDto) {
        Cart cart = cartMapper.convertFromDtoToEntity(cartDto, Cart.class);
        cart.setTotalItems(0);
        cart.setTotalPrice(0.0);

        Cart saved = cartRepository.save(cart);
        return cartMapper.convertFromEntityToDto(saved, CartDto.class);
    }

    @Override
    public CartDto addProductToCart(String userId, String productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    return newCart;
                });

        // For demo: add a dummy product (ideally fetch from Product service)
        CartItem item = new CartItem(productId, "Product " + productId, 100.0, 1);

        // if product already exists, increase quantity
        CartItem existing = cart.getItems().stream()
                .filter(ci -> ci.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + 1);
        } else {
            cart.addItem(item);
        }

        cart.recalculateTotals();
        Cart saved = cartRepository.save(cart);
        return cartMapper.convertFromEntityToDto(saved, CartDto.class);
    }

    @Override
    public CartDto removeProductFromCart(String userId, String productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for userId: " + userId));

        CartItem toRemove = cart.getItems().stream()
                .filter(ci -> ci.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (toRemove != null) {
            cart.removeItem(toRemove);
        }

        cart.recalculateTotals();
        Cart updated = cartRepository.save(cart);
        return cartMapper.convertFromEntityToDto(updated, CartDto.class);
    }

    @Override
    public CartDto getCartByUserId(String userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for userId: " + userId));

        return cartMapper.convertFromEntityToDto(cart, CartDto.class);
    }

    @Override
    public List<CartDto> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream()
                .map(c -> cartMapper.convertFromEntityToDto(c, CartDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void clearCart(String userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for userId: " + userId));

        cart.getItems().clear();
        cart.setTotalItems(0);
        cart.setTotalPrice(0.0);

        cartRepository.save(cart);
    }
}
