package com.josealam.pingpongexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.josealam.pingpongexercise.models.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Long>
{
    
}
