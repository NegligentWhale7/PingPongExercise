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
        

    }

    @Test
    void testInvalidDescription() 
    {
        WorkshopDTO workshopDTO = new WorkshopDTO();
        workshopDTO.setName("Taller de Prueba");
        workshopDTO.setDescription("Corta"); // Invalid description, too short

        Set<ConstraintViolation<WorkshopDTO>> violations = validator.validate(workshopDTO);
        assertEquals(1, violations.size(), "Should have one validation error for description");
    }
}
