package com.fulfilment.application.monolith.warehouses.domain.models;

import java.time.LocalDateTime;

public class WarehouseMetrics {

    public String businessUnitCode;

    public Integer currentStock;

    public Integer reservedStock;

    public Integer availableCapacity;

    public Boolean overCapacity;

    public Boolean safetyBreached;

    public LocalDateTime lastCalculatedAt;

    // helper method (adds branch coverage)
    public boolean isOverloaded() {
        return currentStock != null
                && availableCapacity != null
                && currentStock > availableCapacity;
    }

    public boolean needsAttention() {
        return Boolean.TRUE.equals(overCapacity)
                || Boolean.TRUE.equals(safetyBreached);
    }
}