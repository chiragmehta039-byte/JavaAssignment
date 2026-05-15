package com.fulfilment.application.monolith.warehouses.bin;

import com.fulfilment.application.monolith.warehouses.extra.bin.WarehouseInventory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseInventoryTest {

    @Test
    void availableStock() {
        WarehouseInventory w = new WarehouseInventory();
        w.totalStock = 100;
        w.reservedStock = 20;

        assertEquals(80, w.availableStock());
    }

    @Test
    void nullSafeAvailableStock() {
        WarehouseInventory w = new WarehouseInventory();

        assertEquals(0, w.availableStock());
    }

    @Test
    void overReserved_true() {
        WarehouseInventory w = new WarehouseInventory();
        w.totalStock = 50;
        w.reservedStock = 80;

        assertTrue(w.isOverReserved());
    }

    @Test
    void overReserved_false() {
        WarehouseInventory w = new WarehouseInventory();
        w.totalStock = 100;
        w.reservedStock = 20;

        assertFalse(w.isOverReserved());
    }
}