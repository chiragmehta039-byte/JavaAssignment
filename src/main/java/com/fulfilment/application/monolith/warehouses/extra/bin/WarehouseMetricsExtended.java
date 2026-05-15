package com.fulfilment.application.monolith.warehouses.extra.bin;

public class WarehouseMetricsExtended {

    public Integer capacity;
    public Integer currentStock;
    public Integer incomingStock;

    public int totalProjectedLoad() {
        int stock = currentStock == null ? 0 : currentStock;
        int incoming = incomingStock == null ? 0 : incomingStock;
        return stock + incoming;
    }

    public boolean isOverCapacity() {
        return capacity != null
                && totalProjectedLoad() > capacity;
    }
}