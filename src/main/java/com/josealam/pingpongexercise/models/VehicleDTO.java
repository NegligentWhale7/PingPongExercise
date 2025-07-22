package com.josealam.pingpongexercise.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDTO {
    private String model;
    private String brand;
    private String anio;
    private String color;
    private String vin;
    private Long workshopId;
    private List<Long> partIds;
}
