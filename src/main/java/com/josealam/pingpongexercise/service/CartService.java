package com.josealam.pingpongexercise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josealam.pingpongexercise.models.Cart;
import com.josealam.pingpongexercise.models.CartDTO;
import com.josealam.pingpongexercise.models.Product;
import com.josealam.pingpongexercise.repository.CartRepository;
import com.josealam.pingpongexercise.repository.ProductRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;


    public Cart saveCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setUserId(cartDTO.getUserId());
        cart.setTotalPrice(cartDTO.getTotalPrice());
        
        List<Product> products = productRepository.findAllById(cartDTO.getProductIds());
        cart.setProducts(products);
        
        return cartRepository.save(cart);
    }

    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public String deleteCart(Long id) {
        if (!cartRepository.existsById(id)) {
            throw new RuntimeException("Cart not found");
        }
        cartRepository.deleteById(id);
        return "Cart deleted successfully";
    }

    public Cart updateCart(Long id, CartDTO cartDTO) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        
        
        
        List<Product> products = productRepository.findAllById(cartDTO.getProductIds());
        double totalPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        cart.setTotalPrice(totalPrice);
        if (products.isEmpty()) {
            throw new RuntimeException("No products found for the given IDs");
        }
        cart.setProducts(products);
        
        return cartRepository.save(cart);
    }

}
