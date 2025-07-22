package com.josealam.pingpongexercise.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josealam.pingpongexercise.models.Workshop;
import com.josealam.pingpongexercise.models.WorkshopDTO;
import com.josealam.pingpongexercise.service.WorkshopService;

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
public class WorkshopController 
{
    private final WorkshopService workshopService;
    public WorkshopController(WorkshopService workshopService) 
    {
        this.workshopService = workshopService;
    }

    @GetMapping
    public ResponseEntity<List<Workshop>> getWorkshops() 
    {
        List<Workshop> workshopList = workshopService.getAllWorkshops();
        return ResponseEntity.ok(workshopList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Workshop> getWorkshopById(@PathVariable long id) {
        var workshop = workshopService.getWorkshopById(id);
        if(!workshop.isPresent()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(workshop.get());
    }

    @PostMapping
    public ResponseEntity<Workshop> postWorkshop(@Valid @RequestBody WorkshopDTO entity) 
    {
       Workshop workshop = workshopService.createWorkshop(entity);
       if (workshop == null) return ResponseEntity.internalServerError().body(null);
       return ResponseEntity.ok(workshop);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workshop> putWorkshopById(@PathVariable long id, @Valid @RequestBody WorkshopDTO entity) 
    {
       Workshop workshop = workshopService.updateWorkshopById(id, entity);
       if (workshop == null) return ResponseEntity.internalServerError().body(null);
       return ResponseEntity.ok(workshop);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkshop(@PathVariable Long id)
    {
        workshopService.deleteWorkshop(id);
        return ResponseEntity.noContent().build();
    }
}
