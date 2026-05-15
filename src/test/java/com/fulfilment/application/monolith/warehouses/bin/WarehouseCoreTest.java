package com.fulfilment.application.monolith.warehouses.bin;

import com.fulfilment.application.monolith.warehouses.extra.bin.WarehouseCore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseCoreTest {

    @Test
    void validWarehouse() {
        WarehouseCore w = new WarehouseCore();
        w.businessUnitCode = "W1";
        w.capacity = 100;

        assertTrue(w.isValid());
    }

    @Test
    void invalidWarehouse_nullCode() {
        WarehouseCore w = new WarehouseCore();
        w.capacity = 100;

        assertFalse(w.isValid());
    }

    @Test
    void invalidWarehouse_zeroCapacity() {
        WarehouseCore w = new WarehouseCore();
        w.businessUnitCode = "W1";
        w.capacity = 0;

        assertFalse(w.isValid());
    }
}