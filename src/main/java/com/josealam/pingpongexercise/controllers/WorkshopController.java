package com.josealam.pingpongexercise.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josealam.pingpongexercise.models.Workshop;
import com.josealam.pingpongexercise.models.WorkshopDTO;
import com.josealam.pingpongexercise.service.WorkshopService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




/* @author Alam Armas */
@RestController
@RequestMapping("/api/v1/workshop")
@Tag(name = "Workshop", description = "API para gestión de talleres")
public class WorkshopController 
{
    private final WorkshopService workshopService;
    public WorkshopController(WorkshopService workshopService) 
    {
        this.workshopService = workshopService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los talleres", description = "Retorna una lista de todos los talleres registrados")
    @ApiResponse(responseCode = "200", description = "Lista de talleres obtenida exitosamente")
    public ResponseEntity<List<Workshop>> getWorkshops() 
    {
        List<Workshop> workshopList = workshopService.getAllWorkshops();
        return ResponseEntity.ok(workshopList);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener taller por ID", description = "Retorna un taller específico basado en su ID")
    @ApiResponse(responseCode = "200", description = "Taller encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Taller no encontrado")
    public ResponseEntity<Workshop> getWorkshopById(@PathVariable long id) {
        var workshop = workshopService.getWorkshopById(id);
        if(!workshop.isPresent()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(workshop.get());
    }

    @PostMapping
    @Operation(summary = "Crear nuevo taller", description = "Crea un nuevo taller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Taller creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos - revise los errores de validación")
    })
    public ResponseEntity<Workshop> postWorkshop(@Valid @RequestBody WorkshopDTO entity) 
    {
       Workshop workshop = workshopService.createWorkshop(entity);
       if (workshop == null) return ResponseEntity.internalServerError().body(null);
       return ResponseEntity.ok(workshop);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar taller por ID", description = "Actualiza un taller existente basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Taller actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos - revise los errores de validación"),
            @ApiResponse(responseCode = "404", description = "Taller no encontrado")
    })
    public ResponseEntity<Workshop> putWorkshopById(@PathVariable long id, @Valid @RequestBody WorkshopDTO entity) 
    {
       Workshop workshop = workshopService.updateWorkshopById(id, entity);
       if (workshop == null) return ResponseEntity.internalServerError().body(null);
       return ResponseEntity.ok(workshop);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar taller por ID", description = "Elimina un taller basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Taller eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Taller no encontrado")
    })
    public ResponseEntity<Void> deleteWorkshop(@PathVariable Long id)
    {
        workshopService.deleteWorkshop(id);
        return ResponseEntity.noContent().build();
    }
}
