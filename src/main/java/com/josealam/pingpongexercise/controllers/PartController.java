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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {
    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }
    @GetMapping("/{id}")
    public Part getPartById(@PathVariable Long id) {
        return partService.getPartById(id);
    }
    @PostMapping("/save")
    public Part savePart(@Valid @RequestBody PartDTO part) {
        return partService.savePart(part);
    } 
    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable Long id) {
        partService.deletePart(id);
    }
    @PutMapping("/{id}")
    public Part updatePart(@PathVariable Long id, @Valid @RequestBody PartDTO partDto) 
    {
        return partService.updatePartById(id, partDto);
    }
}
