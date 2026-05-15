package com.fulfilment.application.monolith.warehouses.domain.models;

public class WarehouseCapacityReport {

    public String warehouseId;

    public Integer maxCapacity;

    public Integer usedCapacity;

    public Integer reservedCapacity;

    public boolean isOverUtilized() {
        return usedCapacity != null
                && maxCapacity != null
                && usedCapacity > maxCapacity;
    }

    public boolean hasFreeSpace() {
        return maxCapacity != null
                && usedCapacity != null
                && usedCapacity < maxCapacity;
    }
}