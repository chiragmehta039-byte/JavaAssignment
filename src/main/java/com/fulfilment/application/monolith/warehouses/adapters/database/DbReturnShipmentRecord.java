package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "return_shipment_record")
public class DbReturnShipmentRecord {

    @Id @GeneratedValue
    public Long id;

    public String returnCode;
    public Integer quantity;
    public String reason;
    public LocalDateTime createdAt;

    public static DbReturnShipmentRecord create(String code, Integer qty, String reason) {
        DbReturnShipmentRecord r = new DbReturnShipmentRecord();
        r.returnCode = code;
        r.quantity = qty;
        r.reason = reason;
        r.createdAt = LocalDateTime.now();

        if (qty == null || qty <= 0) r.reason = "INVALID";
        else if ("DAMAGED".equals(reason)) r.reason = "BLOCKED";
        else r.reason = "ACCEPTED";

        return r;
    }
}