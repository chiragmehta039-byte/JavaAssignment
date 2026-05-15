package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_capacity_snapshot")
public class DbWarehouseCapacitySnapshot {

    @Id
    @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Integer maxCapacity;

    public Integer usedCapacity;

    public Integer reservedCapacity;

    public Integer freeCapacity;

    public String healthStatus;

    public LocalDateTime snapshotTime;

    public static DbWarehouseCapacitySnapshot create(
            String code,
            Integer max,
            Integer used,
            Integer reserved
    ) {

        DbWarehouseCapacitySnapshot s = new DbWarehouseCapacitySnapshot();

        s.warehouseCode = code;
        s.maxCapacity = max;
        s.usedCapacity = used;
        s.reservedCapacity = reserved;
        s.snapshotTime = LocalDateTime.now();

        if (code == null || max == null) {
            s.healthStatus = "INVALID";
            return s;
        }

        int usedVal = used == null ? 0 : used;
        int reservedVal = reserved == null ? 0 : reserved;

        s.freeCapacity = max - (usedVal + reservedVal);

        if (s.freeCapacity < 0) {
            s.healthStatus = "OVERLOADED";
        } else if (s.freeCapacity == 0) {
            s.healthStatus = "FULL";
        } else if (s.freeCapacity < max * 0.2) {
            s.healthStatus = "WARNING";
        } else {
            s.healthStatus = "HEALTHY";
        }

        return s;
    }
}