package com.josealam.pingpongexercise.models;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO para crear o actualizar un carrito")
public class CartDTO {
    @NotNull(message = "El id del usuario es obligatorio")
    @NotBlank(message = "El id del usuario no puede estar vacío")
    @Size(min = 1, max = 3, message = "El id del usuario debe tener entre 1 y 3 caracteres")
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    @NotNull(message = "El total del carrito es obligatorio")
    @NotBlank(message = "El total del carrito no puede estar vacío")    
    @Size(min = 0, max = 10, message = "El total del carrito debe tener entre 1 y 10 caracteres")
    @Schema(description = "Total del carrito", example = "150.75", minimum = "0.0", maximum = "10000.0")
    private double totalPrice;


    private List<Long> productIds;
}
