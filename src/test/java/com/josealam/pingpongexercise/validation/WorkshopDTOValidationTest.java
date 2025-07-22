package com.josealam.pingpongexercise.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.josealam.pingpongexercise.models.WorkshopDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validation;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WorkshopDTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() 
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testEmptyWorkshopDTO() 
    {
        WorkshopDTO workshopDTO = new WorkshopDTO();
        workshopDTO.setName("Wokrshop de Prueba");
        workshopDTO.setDescription("Descripción del taller de prueba");

        Set<ConstraintViolation<WorkshopDTO>> violations = validator.validate(workshopDTO);
        assertTrue(violations.isEmpty(), "WorkshopDTO should not have any validation errors");
    }

    @Test
    void testInvalidName() 
    {
        WorkshopDTO workshopDTO = new WorkshopDTO();
        workshopDTO.setName("A"); // Invalid name, too short
        workshopDTO.setDescription("Descripción del taller de prueba");

        Set<ConstraintViolation<WorkshopDTO>> violations = validator.validate(workshopDTO);
        assertEquals(1, violations.size(), "Should have one validation error for name");
        
        ConstraintViolation<WorkshopDTO> violation = violations.iterator().next();
        assertEquals("El nombre debe tener entre 2 y 200 caracteres", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());

    }

    @Test
    void testInvalidDescription() 
    {
        WorkshopDTO workshopDTO = new WorkshopDTO();
        workshopDTO.setName("Taller de Prueba");
        workshopDTO.setDescription("Corta"); // Invalid description, too short

        Set<ConstraintViolation<WorkshopDTO>> violations = validator.validate(workshopDTO);
        assertEquals(1, violations.size(), "Should have one validation error for description");
        
        ConstraintViolation<WorkshopDTO> violation = violations.iterator().next();
        assertEquals("La descripción debe tener entre 10 y 300 caracteres", violation.getMessage());
        assertEquals("description", violation.getPropertyPath().toString());
    }
}
