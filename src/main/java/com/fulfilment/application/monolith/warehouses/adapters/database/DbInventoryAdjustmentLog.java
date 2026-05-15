package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_adjustment_log")
public class DbInventoryAdjustmentLog {

    @Id @GeneratedValue
    public Long id;

    public String sku;
    public Integer delta;

    public String type;

    public LocalDateTime time;

    public static DbInventoryAdjustmentLog create(String sku, Integer delta) {
        DbInventoryAdjustmentLog l = new DbInventoryAdjustmentLog();
        l.sku = sku;
        l.delta = delta;
        l.time = LocalDateTime.now();

        if (delta == null) l.type = "IGNORE";
        else if (delta > 0) l.type = "INCREASE";
        else l.type = "DECREASE";

        return l;
    }
}