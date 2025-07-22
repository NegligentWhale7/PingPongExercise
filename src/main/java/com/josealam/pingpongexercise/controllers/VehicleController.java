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

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Vehicles", description = "API para gestión de vehículos")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los vehículos", description = "Retorna una lista de todos los vehículos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de vehículos obtenida exitosamente")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener vehículo por ID", description = "Retorna un vehículo específico basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehículo encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Vehículo no encontrado")
    })
    public Vehicle getVehicleById(@Parameter(description = "ID del vehículo", required = true) @PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping("/save")
    @Operation(summary = "Crear nuevo vehículo", description = "Crea un nuevo vehículo con validación Jakarta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Vehículo creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos - revise los errores de validación")
    })
    public Vehicle saveVehicle(@Valid @RequestBody VehicleDTO vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar vehículo", description = "Actualiza un vehículo existente con validación Jakarta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehículo actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos - revise los errores de validación"),
        @ApiResponse(responseCode = "404", description = "Vehículo no encontrado")
    })
    public Vehicle updateVehicle(
            @Parameter(description = "ID del vehículo a actualizar", required = true) @PathVariable Long id, 
            @Valid @RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.updateVehicle(id, vehicleDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar vehículo", description = "Elimina un vehículo basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Vehículo eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Vehículo no encontrado")
    })
    public void deleteVehicle(@Parameter(description = "ID del vehículo a eliminar", required = true) @PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }

}
