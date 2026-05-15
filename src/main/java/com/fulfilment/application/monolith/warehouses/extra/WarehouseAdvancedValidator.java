package com.fulfilment.application.monolith.warehouses.extra;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;

public class WarehouseAdvancedValidator {

    public static void validate(Warehouse w) {

        if (w == null)
            throw new IllegalArgumentException("Warehouse is null");

        if (w.businessUnitCode == null || w.businessUnitCode.isBlank())
            throw new IllegalArgumentException("Invalid code");

        if (w.location == null || w.location.isBlank())
            throw new IllegalArgumentException("Invalid location");

        if (w.capacity == null || w.capacity <= 0)
            throw new IllegalArgumentException("Invalid capacity");

        if (w.stock < 0)
            throw new IllegalArgumentException("Stock cannot be negative");

        if (w.stock > w.capacity)
            throw new IllegalArgumentException("Stock exceeds capacity");
    }
}