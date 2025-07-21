package com.josealam.pingpongexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josealam.pingpongexercise.models.Part;

public interface PartRepository extends JpaRepository<Part, Long> {
    // This interface will automatically provide CRUD operations for Part entities
    
}
