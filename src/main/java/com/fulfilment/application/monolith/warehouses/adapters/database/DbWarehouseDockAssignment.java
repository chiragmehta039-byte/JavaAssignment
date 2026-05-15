package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_dock_assignment")
public class DbWarehouseDockAssignment {

    @Id @GeneratedValue
    public Long id;

    public String dockId;
    public String warehouseCode;
    public Boolean occupied;
    public String status;

    public static DbWarehouseDockAssignment create(String dockId, String code, Boolean occupied) {
        DbWarehouseDockAssignment d = new DbWarehouseDockAssignment();
        d.dockId = dockId;
        d.warehouseCode = code;
        d.occupied = occupied;

        if (occupied == null) d.status = "UNKNOWN";
        else if (Boolean.TRUE.equals(occupied)) d.status = "BUSY";
        else d.status = "FREE";

        return d;
    }
}