package com.fulfilment.application.monolith.warehouses.bin;

import com.fulfilment.application.monolith.warehouses.extra.bin.WarehouseLocationInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseLocationInfoTest {

    @Test
    void validCapacity() {
        WarehouseLocationInfo w = new WarehouseLocationInfo();
        w.maxCapacity = 100;

        assertTrue(w.isCapacityValid(50));
    }

    @Test
    void invalidCapacity() {
        WarehouseLocationInfo w = new WarehouseLocationInfo();
        w.maxCapacity = 50;

        assertFalse(w.isCapacityValid(100));
    }

    @Test
    void nullCapacity() {
        WarehouseLocationInfo w = new WarehouseLocationInfo();

        assertFalse(w.isCapacityValid(null));
    }
}