package com.josealam.pingpongexercise.models;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {


    @NotNull(message = "El nombre de la pieza es obligatorio")
    @NotBlank(message = "El nombre de la pieza no puede estar vacío")
    @Size(min = 2, max = 200, message = "El nombre debe tener entre 2 y 200 caracteres")
    private String name;

    @NotNull(message = "La descripción de la pieza es obligatoria")
    @NotBlank(message = "La descripción de la pieza no puede estar vacía")
    @Size(min = 2, max = 500, message = "La descripción de la pieza debe tener entre 10 y 500 caracteres")
    private String description;

    @NotNull(message = "El precio de la pieza es obligatorio")
    @Positive(message = "El precio de la pieza debe ser un número positivo")
    @DecimalMin(value = "0.1", message = "El precio de la pieza debe ser mayor que cero")
    private Double price;

    @NotBlank(message = "La categoría de la pieza no puede estar vacía")
    @Size(min = 2, max = 50, message = "La categoría de la pieza debe tener entre 2 y 50 caracteres")
    private String category;

}
