package com.josealam.pingpongexercise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.josealam.pingpongexercise.models.Part;
import com.josealam.pingpongexercise.repository.PartRepository;

@Service
public class PartService {
    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public Part savePart(Part part) {
        return partRepository.save(part);
    }

    public Part getPartById(Long id) {
        return partRepository.findById(id).orElse(null);
    }

    public List<Part> getAllParts() {
        return partRepository.findAll();
    }
    
    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }
}
