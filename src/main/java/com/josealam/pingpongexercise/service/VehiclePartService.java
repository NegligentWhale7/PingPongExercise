package com.josealam.pingpongexercise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.josealam.pingpongexercise.models.Vehicle;
import com.josealam.pingpongexercise.models.Part;
import jakarta.transaction.Transactional;

@Service
public class VehiclePartService 
{
    private final PartService partService;
    private final VehicleService vehicleService;

    public VehiclePartService(PartService partService, VehicleService vehicleService) 
    {
        this.partService = partService;
        this.vehicleService = vehicleService;
    }

    @Transactional
    public Vehicle addPartToVehicle(Long vehicleId, Long partId) 
    {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found");
        }

        Part part = partService.getPartById(partId);
        if (part == null) {
            throw new IllegalArgumentException("Part not found");
        }
        if (vehicle.getParts() == null) vehicle.setParts(new ArrayList<>());
        if (part.getVehicles() == null) part.setVehicles(new ArrayList<>());
        
        if(!vehicle.getParts().contains(part)) {
            vehicle.getParts().add(part);
        }
        if(!part.getVehicles().contains(vehicle)) {
            part.getVehicles().add(vehicle);
        }

        return vehicleService.saveVehicle(vehicle);
    }

    @Transactional
    public Vehicle removePartFromVehicle(Long vehicleId, Long partId)
    {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found");
        }

        Part part = partService.getPartById(partId);
        if (part == null) {
            throw new IllegalArgumentException("Part not found");
        }

        if(vehicle.getParts() != null) {
            vehicle.getParts().remove(part);
        }
        if(part.getVehicles() != null) {
            part.getVehicles().remove(vehicle);
        }

        return vehicleService.saveVehicle(vehicle);
    }

    public List<Part> getPartsByVehicleId(Long vehicleId) 
    {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found");
        }
        return vehicle.getParts();
    }
}
