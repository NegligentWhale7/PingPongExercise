package com.josealam.pingpongexercise.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.josealam.pingpongexercise.models.VehicleDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validation;

import java.util.Set;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehicleDTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidVehicleDTO() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setModel("Toyota Corolla");
        vehicleDTO.setBrand("Toyota");
        vehicleDTO.setAnio("2023");
        vehicleDTO.setColor("Rojo");
        vehicleDTO.setVin("1HGBH41JXMN109186");
        vehicleDTO.setWorkshopId(1L);
        vehicleDTO.setPartIds(List.of(1L, 2L, 3L));

        Set<ConstraintViolation<VehicleDTO>> violations = validator.validate(vehicleDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidVehicleDTO_NullModel() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setBrand("Toyota");
        vehicleDTO.setAnio("2023");
        vehicleDTO.setWorkshopId(1L);

        Set<ConstraintViolation<VehicleDTO>> violations = validator.validate(vehicleDTO);
        assertEquals(1, violations.size());
        
        ConstraintViolation<VehicleDTO> violation = violations.iterator().next();
        assertEquals("El modelo es obligatorio", violation.getMessage());
        assertEquals("model", violation.getPropertyPath().toString());
    }

    @Test
    void testInvalidVehicleDTO_ShortModel() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setModel("A"); // Too short
        vehicleDTO.setBrand("Toyota");
        vehicleDTO.setAnio("2023");
        vehicleDTO.setWorkshopId(1L);

        Set<ConstraintViolation<VehicleDTO>> violations = validator.validate(vehicleDTO);
        assertEquals(1, violations.size());
        
        ConstraintViolation<VehicleDTO> violation = violations.iterator().next();
        assertEquals("El modelo debe tener entre 2 y 100 caracteres", violation.getMessage());
    }

    @Test
    void testInvalidVehicleDTO_InvalidYear() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setModel("Toyota Corolla");
        vehicleDTO.setBrand("Toyota");
        vehicleDTO.setAnio("23"); // Invalid year format
        vehicleDTO.setWorkshopId(1L);

        Set<ConstraintViolation<VehicleDTO>> violations = validator.validate(vehicleDTO);
        assertEquals(1, violations.size());
        
        ConstraintViolation<VehicleDTO> violation = violations.iterator().next();
        assertEquals("El año debe ser un número de 4 dígitos", violation.getMessage());
    }

    @Test
    void testInvalidVehicleDTO_InvalidVIN() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setModel("Toyota Corolla");
        vehicleDTO.setBrand("Toyota");
        vehicleDTO.setAnio("2023");
        vehicleDTO.setVin("INVALID"); // Invalid VIN
        vehicleDTO.setWorkshopId(1L);

        Set<ConstraintViolation<VehicleDTO>> violations = validator.validate(vehicleDTO);
        assertEquals(1, violations.size());
        
        ConstraintViolation<VehicleDTO> violation = violations.iterator().next();
        assertEquals("El VIN debe tener exactamente 17 caracteres alfanuméricos válidos", violation.getMessage());
    }

    @Test
    void testInvalidVehicleDTO_NegativeWorkshopId() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setModel("Toyota Corolla");
        vehicleDTO.setBrand("Toyota");
        vehicleDTO.setAnio("2023");
        vehicleDTO.setWorkshopId(-1L); // Negative ID

        Set<ConstraintViolation<VehicleDTO>> violations = validator.validate(vehicleDTO);
        assertEquals(1, violations.size());
        
        ConstraintViolation<VehicleDTO> violation = violations.iterator().next();
        assertEquals("El ID del taller debe ser un número positivo", violation.getMessage());
    }
}
