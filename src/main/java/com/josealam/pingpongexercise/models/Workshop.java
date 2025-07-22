package com.josealam.pingpongexercise.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/* @author Alam Armas */
@Entity
@Getter
@Setter
public class Workshop 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workshopId;
    private String name;
    private String description;

    @OneToMany(mappedBy = "workshop")
    @JsonIgnore //If we have some time, we should use DTOs
    private List<Vehicle> vehicles;
}
