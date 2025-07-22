package com.josealam.pingpongexercise.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josealam.pingpongexercise.models.Vehicle;
import com.josealam.pingpongexercise.service.VehiclePartService;
import com.josealam.pingpongexercise.models.Part;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/vehicle-parts")
public class VehiclePartController 
{
    private final VehiclePartService vehiclePartService;

    public VehiclePartController(VehiclePartService vehiclePartService) 
    {
        this.vehiclePartService = vehiclePartService;
    }

    @GetMapping("/{id}/parts")
    public ResponseEntity<List<Part>> getPartsByVehicle(@PathVariable Long id) 
    {
        List<Part> parts = vehiclePartService.getPartsByVehicleId(id);
        if(parts == null) return ResponseEntity.internalServerError().body(null);
        return ResponseEntity.ok(parts);
    }
    

    @PostMapping
    public ResponseEntity<Vehicle> assignPartToVehicle(@RequestParam Long vehicleId, @RequestParam Long partId) 
    {
        Vehicle vehicle = vehiclePartService.addPartToVehicle(vehicleId, partId);
        return ResponseEntity.ok(vehicle);
    }

    @DeleteMapping
    public ResponseEntity<Vehicle> removePartFromVehicle(@RequestParam Long vehicleId, @RequestParam Long partId) 
    {
        Vehicle vehicle = vehiclePartService.removePartFromVehicle(vehicleId, partId);
        return ResponseEntity.ok(vehicle);
    }
    
}
