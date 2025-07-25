package com.josealam.pingpongexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josealam.pingpongexercise.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // This interface will automatically provide CRUD operations for Cart entities
    
}
