package com.josealam.pingpongexercise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private long workshopId;
    private String name;
    private String description;
}
