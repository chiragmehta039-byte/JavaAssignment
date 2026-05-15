package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_capacity_audit")
public class DbWarehouseCapacityAudit {

    @Id @GeneratedValue
    public Long id;

    public Integer capacity;
    public Integer used;
    public String flag;
    public LocalDateTime time;

    public static DbWarehouseCapacityAudit create(Integer cap, Integer used) {
        DbWarehouseCapacityAudit a = new DbWarehouseCapacityAudit();
        a.capacity = cap;
        a.used = used;
        a.time = LocalDateTime.now();

        if (cap == null || used == null) a.flag = "INVALID";
        else if (used > cap) a.flag = "OVER";
        else if (used.equals(cap)) a.flag = "FULL";
        else a.flag = "OK";

        return a;
    }
}