package com.josealam.pingpongexercise.service;

import com.josealam.pingpongexercise.repository.WorkshopRepository;
import com.josealam.pingpongexercise.models.Workshop;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/* @author Alam Armas */
@Service
public class WorkshopService {
    private final WorkshopRepository workshopRepository;

    public WorkshopService(WorkshopRepository workshopRepository) 
    {
        this.workshopRepository = workshopRepository;
    }

    public List<Workshop> getAllWorkshops() 
    {
        return workshopRepository.findAll();
    }

    public Optional<Workshop> getWorkshopById(Long id) 
    {
        return workshopRepository.findById(id);
    }

    public Workshop createWorkshop(Workshop workshop) 
    {
        return workshopRepository.save(workshop);
    }

    public Workshop updateWorkshop(Workshop workshop) 
    {
        if (workshop.getWorkshopId() == 0) {
            return null; // or throw an exception
        }
        return workshopRepository.save(workshop);
    }

    public Workshop updateWorkshopById(Long id, Workshop workshop) 
    {
        workshop.setWorkshopId(id); // Ensure the entity has the correct ID
        return workshopRepository.save(workshop);
    }

    public void deleteWorkshop(Long id) 
    {
        workshopRepository.deleteById(id);
    }
}
