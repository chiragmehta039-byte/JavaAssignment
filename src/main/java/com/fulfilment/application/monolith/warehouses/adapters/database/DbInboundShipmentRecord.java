package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inbound_shipment_record")
public class DbInboundShipmentRecord {

    @Id
    @GeneratedValue
    public Long id;

    public String shipmentCode;
    public Integer quantity;
    public String status;
    public LocalDateTime createdAt;

    public static DbInboundShipmentRecord create(String code, Integer qty) {
        DbInboundShipmentRecord r = new DbInboundShipmentRecord();
        r.shipmentCode = code;
        r.quantity = qty;
        r.createdAt = LocalDateTime.now();

        if (qty == null || qty <= 0) {
            r.status = "INVALID";
        } else if (qty > 100) {
            r.status = "HEAVY";
        } else {
            r.status = "NORMAL";
        }

        return r;
    }
}