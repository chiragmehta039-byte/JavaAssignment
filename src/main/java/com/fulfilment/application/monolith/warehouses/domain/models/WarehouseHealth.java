package com.fulfilment.application.monolith.warehouses.domain.models;

import java.time.LocalDateTime;

public class WarehouseHealth {

    public String warehouseCode;

    public Integer temperature;

    public Integer humidity;

    public Boolean powerFailure;

    public Boolean fireAlert;

    public LocalDateTime checkedAt;

    public boolean isCritical() {
        return Boolean.TRUE.equals(powerFailure)
                || Boolean.TRUE.equals(fireAlert);
    }

    public boolean isEnvironmentUnsafe() {
        return temperature != null && temperature > 45
                || humidity != null && humidity > 80;
    }
}