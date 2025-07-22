package com.josealam.pingpongexercise.validation;

import com.josealam.pingpongexercise.models.PartDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validation;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class PartDTOValidationTest 
{
    private Validator validator;
    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testEmptyPartDto()
    {
        PartDTO partDTO = new PartDTO();
        partDTO.setName("Parte de Prueba");
        partDTO.setDescription("Descripción de la parte de prueba");

        Set<ConstraintViolation<PartDTO>> violations = validator.validate(partDTO);
        assertTrue(violations.isEmpty(), "PartDTO should not have any validation errors");
    }

    @Test
    void testInvalidPartDTO_ShortName() {
        PartDTO partDTO = new PartDTO();
        partDTO.setName("A"); // Invalid name, too short
        partDTO.setDescription("Descripción de la parte de prueba");

        Set<ConstraintViolation<PartDTO>> violations = validator.validate(partDTO);
        assertEquals(1, violations.size(), "Should have one validation error for name");

        ConstraintViolation<PartDTO> violation = violations.iterator().next();
        assertEquals("El nombre debe tener entre 2 y 200 caracteres", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }

    @Test
    void testInvalidPartDTO_NullDescription() {
        PartDTO partDTO = new PartDTO();
        partDTO.setName("Parte de Prueba");
        partDTO.setDescription("Hola"); // Invalid description, null
        assertNotNull(partDTO.getDescription(), "Description should not be null");
    }

    @Test
    void testInvalidPartDTO_ShortDescription() {
        PartDTO partDTO = new PartDTO();
        partDTO.setName("Parte de Prueba");
        partDTO.setDescription("Corta"); // Invalid description, too short

        Set<ConstraintViolation<PartDTO>> violations = validator.validate(partDTO);
        assertEquals(1, violations.size(), "Should have one validation error for description");

        ConstraintViolation<PartDTO> violation = violations.iterator().next();
        assertEquals("La descripción debe tener entre 10 y 300 caracteres", violation.getMessage());
        assertEquals("description", violation.getPropertyPath().toString());
    }
}
