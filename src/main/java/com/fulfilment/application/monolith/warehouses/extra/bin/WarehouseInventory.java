package com.fulfilment.application.monolith.warehouses.extra.bin;

public class WarehouseInventory {

    public Integer totalStock;
    public Integer reservedStock;

    public int availableStock() {
        if (totalStock == null || reservedStock == null) return 0;
        return totalStock - reservedStock;
    }

    public boolean isOverReserved() {
        return reservedStock != null
                && totalStock != null
                && reservedStock > totalStock;
    }
}