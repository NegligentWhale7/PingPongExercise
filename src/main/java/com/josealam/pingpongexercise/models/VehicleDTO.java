package com.josealam.pingpongexercise.models;

import java.util.List;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO para crear o actualizar un vehículo")
public class VehicleDTO {

    @NotNull(message = "El modelo es obligatorio")
    @Size(min = 2, max = 100, message = "El modelo debe tener entre 2 y 200 caracteres")
    @Schema(description = "Modelo del vehículo", example = "Toyota Corolla", minLength = 2, maxLength = 100)
    private String model;

    @NotNull(message = "La marca es obligatoria")
    @Size(min = 2, max = 50, message = "La marca debe tener entre 2 y 20 caracteres")
    @Schema(description = "Marca del vehículo", example = "Toyota", minLength = 2, maxLength = 50)
    private String brand;
    
    @NotNull(message = "El año es obligatorio")
    @Pattern(regexp = "\\d{4}", message = "El año debe ser un número de 4 dígitos")
    @Schema(description = "Año de fabricación del vehículo", example = "2023", pattern = "\\d{4}")
    private String anio;
    
    @Size(max = 30, message = "El color no puede exceder 200 caracteres")
    @Schema(description = "Color del vehículo", example = "Rojo", maxLength = 30)
    private String color;
    
    @Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "El VIN debe tener exactamente 17 caracteres alfanuméricos válidos")
    @Schema(description = "Número de identificación del vehículo (VIN)", example = "1HGBH41JXMN109186", pattern = "^[A-HJ-NPR-Z0-9]{17}$")
    private String vin;
    
    @NotNull(message = "El ID del taller es obligatorio")
    @Positive(message = "El ID del taller debe ser un número positivo")
    @Schema(description = "ID del taller donde se encuentra el vehículo", example = "1", minimum = "1")
    private Long workshopId;
    
    @Schema(description = "Lista de IDs de las partes del vehículo", example = "[1, 2, 3]")
    @NotEmpty(message = "La lista de IDs de partes no puede estar vacía")
    private List<
    @Positive(message = "Los IDs de las partes deben ser números positivos") Long> partIds;
}
