package com.josealam.pingpongexercise.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.josealam.pingpongexercise.models.Vehicle;
import com.josealam.pingpongexercise.models.VehicleDTO;
import com.josealam.pingpongexercise.service.VehicleService;
import org.springframework.web.bind.annotation.RequestMapping;




@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping("/save")
    public Vehicle saveVehicle(@RequestBody VehicleDTO vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }
    
    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.updateVehicle(id, vehicleDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }

}
