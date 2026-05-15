package com.fulfilment.application.monolith.warehouses.extra.bin;

public class WarehouseLocationInfo {

    public String locationCode;
    public Integer maxCapacity;
    public String region;

    public boolean isCapacityValid(Integer requested) {
        return requested != null
                && maxCapacity != null
                && requested <= maxCapacity;
    }
}