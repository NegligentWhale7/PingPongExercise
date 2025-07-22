package com.josealam.pingpongexercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josealam.pingpongexercise.models.Part;
import com.josealam.pingpongexercise.models.Vehicle;
import com.josealam.pingpongexercise.models.VehicleDTO;
import com.josealam.pingpongexercise.models.Workshop;
import com.josealam.pingpongexercise.repository.PartRepository;
import com.josealam.pingpongexercise.repository.VehicleRepository;
import com.josealam.pingpongexercise.repository.WorkshopRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private WorkshopRepository workshopRepository;
    
    
    public Vehicle saveVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setAnio(vehicleDTO.getAnio());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setVin(vehicleDTO.getVin());
        Workshop workshop = workshopRepository.findById(vehicleDTO.getWorkshopId())
                .orElseThrow(() -> new RuntimeException("Workshop not found"));
        vehicle.setWorkshop(workshop);
        
        List<Part> parts = partRepository.findAllById(vehicleDTO.getPartIds());
        if (parts.isEmpty()) {
            throw new RuntimeException("No parts found for the given IDs"); 
        }  
        vehicle.setParts(parts);
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setAnio(vehicleDTO.getAnio());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setVin(vehicleDTO.getVin());
        
        Workshop workshop = workshopRepository.findById(vehicleDTO.getWorkshopId())
                .orElseThrow(() -> new RuntimeException("Workshop not found"));
        vehicle.setWorkshop(workshop);
        
        List<Part> parts = partRepository.findAllById(vehicleDTO.getPartIds());
        if (parts.isEmpty()) {
            throw new RuntimeException("No parts found for the given IDs"); 
        }   
        vehicle.setParts(parts);
        
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
