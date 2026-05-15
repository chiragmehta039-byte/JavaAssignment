package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_packing_status")
public class DbWarehousePackingStatus {

    @Id @GeneratedValue
    public Long id;

    public String orderId;
    public Integer items;
    public String status;

    public static DbWarehousePackingStatus create(String orderId, Integer items) {
        DbWarehousePackingStatus p = new DbWarehousePackingStatus();
        p.orderId = orderId;
        p.items = items;

        if (items == null) p.status = "UNKNOWN";
        else if (items == 0) p.status = "EMPTY";
        else if (items > 20) p.status = "HEAVY";
        else p.status = "NORMAL";

        return p;
    }
}