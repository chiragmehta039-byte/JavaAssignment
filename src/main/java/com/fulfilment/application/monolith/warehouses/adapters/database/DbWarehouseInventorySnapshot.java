package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_inventory_snapshot")
public class DbWarehouseInventorySnapshot {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Integer totalItems;

    public Integer reservedItems;

    public Integer availableItems;

    public LocalDateTime snapshotAt;

    public static DbWarehouseInventorySnapshot build(String code, Integer total, Integer reserved) {

        DbWarehouseInventorySnapshot s = new DbWarehouseInventorySnapshot();

        s.warehouseCode = code;
        s.totalItems = total;
        s.reservedItems = reserved;
        s.snapshotAt = LocalDateTime.now();

        if (code == null) {
            s.availableItems = -1;
        } else if (total == null || reserved == null) {
            s.availableItems = 0;
        } else {
            s.availableItems = total - reserved;
        }

        return s;
    }
}