package com.josealam.pingpongexercise.models;

import java.util.List;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDTO {

    @NotNull(message = "El modelo es obligatorio")
    @Size(min = 2, max = 100, message = "El modelo debe tener entre 2 y 200 caracteres")
    private String model;

    @NotNull(message = "La marca es obligatoria")
    @Size(min = 2, max = 50, message = "La marca debe tener entre 2 y 20 caracteres")
    private String brand;
    
    @NotNull(message = "El año es obligatorio")
    @Pattern(regexp = "\\d{4}", message = "El año debe ser un número de 4 dígitos")
    private String anio;
    
    @Size(max = 30, message = "El color no puede exceder 200 caracteres")
    private String color;
    
    @Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "El VIN debe tener exactamente 17 caracteres alfanuméricos válidos")
    private String vin;
    
    @NotNull(message = "El ID del taller es obligatorio")
    @Positive(message = "El ID del taller debe ser un número positivo")
    private Long workshopId;
    
    private List<@Positive(message = "Los IDs de las partes deben ser números positivos") Long> partIds;
}
