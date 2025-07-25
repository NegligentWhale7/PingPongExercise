package com.josealam.pingpongexercise.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josealam.pingpongexercise.models.Cart;
import com.josealam.pingpongexercise.models.CartDTO;
import com.josealam.pingpongexercise.models.Product;
import com.josealam.pingpongexercise.models.ProductDTO;
import com.josealam.pingpongexercise.models.Workshop;
import com.josealam.pingpongexercise.models.WorkshopDTO;
import com.josealam.pingpongexercise.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/carts")
@Tag(name = "Carts", description = "API para gestión de carritos de compras")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los carritos", description = "Retorna una lista de todos los carritos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de carritos obtenida exitosamente")
    @ApiResponse(responseCode = "404", description = "Carritos no encontrados")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener carrito por ID", description = "Retorna un carrito específico por su ID")
    @ApiResponse(responseCode = "200", description = "Carrito obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    public Cart getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Crear nuevo carrito", description = "Crea un nuevo carrito de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrito creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos - revise los errores de validación")
    })
    public Cart saveCart(@RequestBody CartDTO cartDto) {
        return cartService.saveCart(cartDto);
    }

   

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Actualizar carrito", description = "Actualiza un carrito existente")
    @ApiResponse(responseCode = "200", description = "Carrito actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    public Cart updateCart(@PathVariable Long id, @RequestBody CartDTO cartDto) {
        return cartService.updateCart(id, cartDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")   
    @Operation(summary = "Eliminar carrito por ID", description = "Elimina un carrito basado en su ID")
    @ApiResponse(responseCode = "204", description = "Carrito eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
}
