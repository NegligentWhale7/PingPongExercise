package com.josealam.pingpongexercise.service;

import com.josealam.pingpongexercise.repository.WorkshopRepository;
import com.josealam.pingpongexercise.models.Workshop;
import com.josealam.pingpongexercise.models.WorkshopDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/* @author Alam Armas */
@Service
public class WorkshopService 
{
    private final WorkshopRepository workshopRepository;

    public WorkshopService(WorkshopRepository workshopRepository) {
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

    public Workshop createWorkshop(WorkshopDTO workshopDto) 
    {
        Workshop workshop = new Workshop();
        workshop.setName(workshopDto.getName().trim());
        workshop.setDescription(workshopDto.getDescription().trim());
        return workshopRepository.save(workshop);
    }

    public Workshop updateWorkshop(WorkshopDTO workshopDto) 
    {
        Workshop workshop = new Workshop();
        workshop.setName(workshopDto.getName());
        workshop.setDescription(workshopDto.getDescription());
        if (workshop.getWorkshopId() == 0) {
            return null; // or throw an exception
        }
        return workshopRepository.save(workshop);
    }

    public Workshop updateWorkshopById(Long id, WorkshopDTO workshopDto) 
    {
        Workshop workshop = new Workshop();
        workshop.setName(workshopDto.getName());
        workshop.setDescription(workshopDto.getDescription());
        workshop.setWorkshopId(id); // Ensure the entity has the correct ID
        return workshopRepository.save(workshop);
    }

    public void deleteWorkshop(Long id) 
    {
        workshopRepository.deleteById(id);
    }
}
