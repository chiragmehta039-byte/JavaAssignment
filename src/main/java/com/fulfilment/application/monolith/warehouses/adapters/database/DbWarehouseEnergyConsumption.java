package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_energy")
public class DbWarehouseEnergyConsumption {

    @Id @GeneratedValue
    public Long id;

    public Integer units;

    public String grade;

    public static DbWarehouseEnergyConsumption create(Integer units) {
        DbWarehouseEnergyConsumption e = new DbWarehouseEnergyConsumption();
        e.units = units;

        if (units == null) e.grade = "UNKNOWN";
        else if (units > 1000) e.grade = "HIGH";
        else if (units > 500) e.grade = "MEDIUM";
        else e.grade = "LOW";

        return e;
    }
}