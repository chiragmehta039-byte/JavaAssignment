package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_slot_allocation")
public class DbWarehouseSlotAllocation {

    @Id @GeneratedValue
    public Long id;

    public String slotId;
    public Integer capacity;
    public Integer used;
    public String status;
    public LocalDateTime createdAt;

    public static DbWarehouseSlotAllocation create(String slotId, Integer capacity, Integer used) {
        DbWarehouseSlotAllocation s = new DbWarehouseSlotAllocation();
        s.slotId = slotId;
        s.capacity = capacity;
        s.used = used;
        s.createdAt = LocalDateTime.now();

        if (capacity == null || used == null) {
            s.status = "UNKNOWN";
        } else if (used > capacity) {
            s.status = "OVERFLOW";
        } else if (used.equals(capacity)) {
            s.status = "FULL";
        } else {
            s.status = "AVAILABLE";
        }

        return s;
    }
}