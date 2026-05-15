package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_shipment_tracker")
public class DbWarehouseShipmentTracker {

    @Id @GeneratedValue
    public Long id;

    public String shipmentId;
    public String warehouseCode;
    public String status;

    public LocalDateTime updatedAt;

    public static DbWarehouseShipmentTracker create(String shipmentId, String warehouseCode, String status) {
        DbWarehouseShipmentTracker t = new DbWarehouseShipmentTracker();

        t.shipmentId = shipmentId;
        t.warehouseCode = warehouseCode;
        t.updatedAt = LocalDateTime.now();

        if (shipmentId == null || warehouseCode == null) {
            t.status = "INVALID";
        } else if ("IN_TRANSIT".equals(status)) {
            t.status = "MOVING";
        } else if ("DELIVERED".equals(status)) {
            t.status = "COMPLETED";
        } else if ("FAILED".equals(status)) {
            t.status = "ERROR";
        } else {
            t.status = "PENDING";
        }

        return t;
    }
}