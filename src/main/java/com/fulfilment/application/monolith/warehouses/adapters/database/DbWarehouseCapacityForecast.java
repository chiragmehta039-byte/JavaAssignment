package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_capacity_forecast")
public class DbWarehouseCapacityForecast {

    @Id @GeneratedValue
    public Long id;

    public Integer predictedUsage;
    public Integer currentCapacity;
    public String riskLevel;

    public static DbWarehouseCapacityForecast create(Integer predicted, Integer capacity) {
        DbWarehouseCapacityForecast f = new DbWarehouseCapacityForecast();
        f.predictedUsage = predicted;
        f.currentCapacity = capacity;

        if (predicted == null || capacity == null) {
            f.riskLevel = "UNKNOWN";
        } else if (predicted > capacity * 2) {
            f.riskLevel = "CRITICAL";
        } else if (predicted > capacity) {
            f.riskLevel = "HIGH";
        } else {
            f.riskLevel = "SAFE";
        }

        return f;
    }
}