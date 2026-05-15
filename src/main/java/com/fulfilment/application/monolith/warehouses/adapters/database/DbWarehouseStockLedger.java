package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_stock_ledger")
public class DbWarehouseStockLedger {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Integer inbound;

    public Integer outbound;

    public Integer currentStock;

    public LocalDateTime updatedAt;

    public static DbWarehouseStockLedger calculate(String code, Integer in, Integer out) {

        DbWarehouseStockLedger l = new DbWarehouseStockLedger();

        l.warehouseCode = code;
        l.inbound = in;
        l.outbound = out;
        l.updatedAt = LocalDateTime.now();

        if (code == null) {
            l.currentStock = -1;
        } else if (in == null || out == null) {
            l.currentStock = 0;
        } else {
            l.currentStock = in - out;
        }

        return l;
    }
}