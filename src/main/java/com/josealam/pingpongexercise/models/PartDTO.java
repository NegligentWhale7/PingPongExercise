package com.josealam.pingpongexercise.models;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartDTO {
    @NotNull(message = "El nombre del taller es obligatorio")
    @Size(min = 2, max = 200, message = "El nombre debe tener entre 2 y 200 caracteres")
    private String name;
    @NotNull(message = "La descripción del taller es obligatoria")
    @Size(min = 10, max = 300, message = "La descripción debe tener entre 10 y 300 caracteres")
    private String description;
    
}
