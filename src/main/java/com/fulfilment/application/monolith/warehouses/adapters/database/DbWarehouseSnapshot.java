package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_snapshot")
public class DbWarehouseSnapshot {

    @Id
    @GeneratedValue
    public Long id;

    public String businessUnitCode;

    public Integer stock;

    public Integer capacity;

    public LocalDateTime snapshotTime;

    public String state; // HEALTHY, WARNING, CRITICAL

    public DbWarehouseSnapshot() {}

    public static DbWarehouseSnapshot create(String code, Integer stock, Integer capacity) {
        DbWarehouseSnapshot s = new DbWarehouseSnapshot();
        s.businessUnitCode = code;
        s.stock = stock;
        s.capacity = capacity;
        s.snapshotTime = LocalDateTime.now();

        if (stock == null || capacity == null) {
            s.state = "CRITICAL";
        } else if (stock > capacity) {
            s.state = "WARNING";
        } else {
            s.state = "HEALTHY";
        }

        return s;
    }
}