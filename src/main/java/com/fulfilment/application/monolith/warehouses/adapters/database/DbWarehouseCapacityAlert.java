package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_capacity_alert")
public class DbWarehouseCapacityAlert {

    @Id
    @GeneratedValue
    public Long id;

    public Integer capacity;
    public Integer used;
    public String level;

    public LocalDateTime createdAt;

    public static DbWarehouseCapacityAlert create(Integer capacity, Integer used) {
        DbWarehouseCapacityAlert a = new DbWarehouseCapacityAlert();
        a.capacity = capacity;
        a.used = used;
        a.createdAt = LocalDateTime.now();

        if (capacity == null || used == null) {
            a.level = "UNKNOWN";
        } else if (used > capacity) {
            a.level = "CRITICAL";
        } else if (used.equals(capacity)) {
            a.level = "FULL";
        } else {
            a.level = "SAFE";
        }

        return a;
    }
}