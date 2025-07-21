package com.josealam.pingpongexercise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.josealam.pingpongexercise.models.Vehicle;
import com.josealam.pingpongexercise.repository.VehicleRepository;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }   

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }   
}
