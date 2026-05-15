package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_dispatch_engine")
public class DbWarehouseDispatchEngine {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Integer pendingOrders;

    public Integer capacity;

    public String dispatchStatus;

    public LocalDateTime evaluatedAt;

    public static DbWarehouseDispatchEngine evaluate(String code, Integer pending, Integer capacity) {

        DbWarehouseDispatchEngine d = new DbWarehouseDispatchEngine();

        d.warehouseCode = code;
        d.pendingOrders = pending;
        d.capacity = capacity;
        d.evaluatedAt = LocalDateTime.now();

        if (code == null || code.isBlank()) {
            d.dispatchStatus = "INVALID";
        } else if (pending == null || capacity == null) {
            d.dispatchStatus = "UNKNOWN";
        } else if (pending > capacity) {
            d.dispatchStatus = "OVERLOADED";
        } else if (pending.equals(capacity)) {
            d.dispatchStatus = "FULL_LOAD";
        } else {
            d.dispatchStatus = "READY";
        }

        return d;
    }
}