package com.josealam.pingpongexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josealam.pingpongexercise.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // This interface will automatically provide CRUD operations for Product entities
    
}
