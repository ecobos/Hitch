package org.lbc.hitch.domain;

import java.util.Calendar;

/**
 * Created by Kelby on 4/8/2016
 * TODO write unit test
 */
public class Vehicle {
    private String vehicleId;
    private String make; // TODO consider making enum with brands
    private String model;
    private String color;
    private Integer year;

    public Vehicle(String vehicleId, String make, String model, String color, Integer year) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Integer getYear() {
        return year;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // TODO what should the year be set if an invalid year is passed in
    public void setYear(Integer year) {
        this.year = validateYear(year) ? year:null;
    }

    private boolean validateYear(Integer year) {
        Calendar calendar = Calendar.getInstance();
        Integer currentYear = calendar.get(Calendar.YEAR);
        return currentYear.equals(year);
    }
}
