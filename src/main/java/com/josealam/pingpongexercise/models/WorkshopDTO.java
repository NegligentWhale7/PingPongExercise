package com.josealam.pingpongexercise.models;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkshopDTO {
    @NotBlank(message = "El nombre del taller no puede estar vacío")
    @NotNull(message = "El nombre del taller es obligatorio")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "El nombre del taller solo puede contener letras y números")
    @Size(min = 2, max = 200, message = "El nombre debe tener entre 2 y 200 caracteres")
    private String name;
    @NotNull(message = "La descripción del taller es obligatoria")
    @NotBlank(message = "La descripción del taller no puede estar vacía")
    @Size(min = 10, max = 300, message = "La descripción debe tener entre 10 y 300 caracteres")
    private String description;
}
