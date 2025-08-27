package com.dev.cart.service.controller;

import com.dev.cart.service.dto.CartDto;
import com.dev.cart.service.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping("/create")
	public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) {
		CartDto created = cartService.createCart(cartDto);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@PostMapping("/add")
	public ResponseEntity<CartDto> addProductToCart(@RequestParam String userId, @RequestParam String productId) {
		CartDto updated = cartService.addProductToCart(userId, productId);
		return ResponseEntity.ok(updated);
	}

	@GetMapping("/all")
	public ResponseEntity<List<CartDto>> getAllCarts() {
		List<CartDto> carts = cartService.getAllCarts();
		return ResponseEntity.ok(carts);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<CartDto> getCartByUserId(@PathVariable String userId) {
		CartDto cart = cartService.getCartByUserId(userId);
		return ResponseEntity.ok(cart);
	}

	@DeleteMapping("/remove")
	public ResponseEntity<CartDto> removeProductFromCart(@RequestParam String userId, @RequestParam String productId) {
		CartDto updated = cartService.removeProductFromCart(userId, productId);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/clear/{userId}")
	public ResponseEntity<String> clearCart(@PathVariable String userId) {
		cartService.clearCart(userId);
		return ResponseEntity.ok("Cart cleared successfully for user: " + userId);
	}
}
