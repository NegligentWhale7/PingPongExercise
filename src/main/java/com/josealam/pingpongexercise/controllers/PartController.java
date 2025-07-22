package com.josealam.pingpongexercise.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josealam.pingpongexercise.models.Part;
import com.josealam.pingpongexercise.models.PartDTO;
import com.josealam.pingpongexercise.service.PartService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/parts")
@Tag(name = "Parts", description = "API para gestión de partes de vehículos")
public class PartController {
    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping
    @Operation (summary = "Obtener todas las partes", description = "Retorna una lista de todas las partes registradas")
    @ApiResponse(responseCode = "200", description = "Lista de partes obtenida exitosamente")
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }
    @GetMapping("/{id}")
    @Operation (summary = "Obtener parte por ID", description = "Retorna una parte específica basada en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Parte encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Parte no encontrada")
    })
    public Part getPartById(@PathVariable Long id) {
        return partService.getPartById(id);
    }
    @PostMapping("/save")
    @Operation (summary = "Crear nueva parte", description = "Crea una nueva parte")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Parte creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos - revise los errores de validación")
    })
    public Part savePart(@Valid @RequestBody PartDTO part) {
        return partService.savePart(part);
    } 
    @DeleteMapping("/{id}")
    @Operation (summary = "Eliminar parte por ID", description = "Elimina una parte basada en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Parte eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Parte no encontrada")
    })
    public void deletePart(@PathVariable Long id) {
        partService.deletePart(id);
    }
    @PutMapping("/{id}")
    @Operation (summary = "Actualizar parte por ID", description = "Actualiza una parte existente basada en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Parte actualizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos - revise los errores de validación"),
        @ApiResponse(responseCode = "404", description = "Parte no encontrada")
    })
    public Part updatePart(@PathVariable Long id, @Valid @RequestBody PartDTO partDto) 
    {
        return partService.updatePartById(id, partDto);
    }
}
