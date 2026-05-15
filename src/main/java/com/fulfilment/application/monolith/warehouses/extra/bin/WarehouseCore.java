package com.fulfilment.application.monolith.warehouses.extra.bin;

import java.time.LocalDateTime;

public class WarehouseCore {

    public String businessUnitCode;
    public String location;
    public Integer capacity;
    public Integer stock;

    public LocalDateTime createdAt;

    public boolean isValid() {
        return businessUnitCode != null
                && !businessUnitCode.isBlank()
                && capacity != null
                && capacity > 0;
    }
}