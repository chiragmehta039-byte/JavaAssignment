package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_event")
@Cacheable
public class DbWarehouseEvent {

    @Id
    @GeneratedValue
    public Long id;

    public String businessUnitCode;

    public String eventType; // LOAD, UNLOAD, TRANSFER

    public Integer quantity;

    public LocalDateTime eventTime;

    public String status;

    public DbWarehouseEvent() {}

    public static DbWarehouseEvent create(String code, String type, Integer qty) {
        DbWarehouseEvent e = new DbWarehouseEvent();
        e.businessUnitCode = code;
        e.eventType = type;
        e.quantity = qty;
        e.eventTime = LocalDateTime.now();

        if (qty == null || qty <= 0) {
            e.status = "INVALID";
        } else if ("LOAD".equals(type)) {
            e.status = "LOADED";
        } else if ("UNLOAD".equals(type)) {
            e.status = "UNLOADED";
        } else {
            e.status = "TRANSFERRED";
        }

        return e;
    }
}