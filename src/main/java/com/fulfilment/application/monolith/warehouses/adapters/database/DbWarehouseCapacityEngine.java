package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_capacity_engine")
public class DbWarehouseCapacityEngine {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Integer capacity;

    public Integer used;

    public String status;

    public static DbWarehouseCapacityEngine compute(String code, Integer cap, Integer used) {
        DbWarehouseCapacityEngine e = new DbWarehouseCapacityEngine();

        e.warehouseCode = code;
        e.capacity = cap;
        e.used = used;

        if (code == null || cap == null || used == null) {
            e.status = "INVALID";
        } else if (used > cap) {
            e.status = "OVERFLOW";
        } else if (used.equals(cap)) {
            e.status = "FULL";
        } else if ((double) used / cap > 0.8) {
            e.status = "HIGH";
        } else {
            e.status = "OK";
        }

        return e;
    }
}