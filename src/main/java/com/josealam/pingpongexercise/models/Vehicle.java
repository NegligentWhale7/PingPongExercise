package com.josealam.pingpongexercise.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String brand;
    private String anio;
    private String color;
    private String vin;

    @ManyToOne
    @JoinColumn(name = "workshop_id", nullable = false)
    private Workshop workshop;

    @ManyToMany
    @JoinTable(name = "vehicle_parts", joinColumns = @JoinColumn(name = "vehicle_id"), 
    inverseJoinColumns = @JoinColumn(name = "part_id", nullable = false))
    @JsonIgnore
    private List<Part> parts = new ArrayList<>();
}
