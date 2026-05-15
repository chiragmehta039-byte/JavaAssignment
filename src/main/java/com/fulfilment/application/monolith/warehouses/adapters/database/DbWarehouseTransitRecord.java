package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_transit_record")
public class DbWarehouseTransitRecord {

    @Id @GeneratedValue
    public Long id;

    public String shipmentId;
    public String state;
    public LocalDateTime time;

    public static DbWarehouseTransitRecord create(String shipmentId, String state) {
        DbWarehouseTransitRecord t = new DbWarehouseTransitRecord();
        t.shipmentId = shipmentId;
        t.state = state;
        t.time = LocalDateTime.now();

        if (state == null) t.state = "UNKNOWN";
        else if ("IN".equals(state)) t.state = "ARRIVED";
        else if ("OUT".equals(state)) t.state = "DISPATCHED";
        else t.state = "HOLD";

        return t;
    }
}