package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_ledger")
public class DbInventoryLedgerEntry {

    @Id
    @GeneratedValue
    public Long id;

    public String sku;
    public Integer change;
    public LocalDateTime time;

    public static DbInventoryLedgerEntry create(String sku, Integer change) {
        DbInventoryLedgerEntry e = new DbInventoryLedgerEntry();
        e.sku = sku;
        e.change = change;
        e.time = LocalDateTime.now();

        if (change == null) {
            e.change = 0;
        }
        return e;
    }
}