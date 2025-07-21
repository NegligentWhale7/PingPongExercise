package com.josealam.pingpongexercise.models;

import java.util.List;

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
    private List<Vehicle> vehicles;
}
