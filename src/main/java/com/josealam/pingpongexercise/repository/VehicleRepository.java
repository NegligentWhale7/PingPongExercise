package com.josealam.pingpongexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josealam.pingpongexercise.models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    // This interface will automatically provide CRUD operations for Vehicle entities
    
}
