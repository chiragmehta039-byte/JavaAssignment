package com.fulfilment.application.monolith.warehouses.bin;

import com.fulfilment.application.monolith.warehouses.extra.bin.WarehouseMetricsExtended;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseMetricsExtendedTest {

    @Test
    void totalProjectedLoad() {
        WarehouseMetricsExtended w = new WarehouseMetricsExtended();
        w.currentStock = 50;
        w.incomingStock = 30;

        assertEquals(80, w.totalProjectedLoad());
    }

    @Test
    void overCapacity_true() {
        WarehouseMetricsExtended w = new WarehouseMetricsExtended();
        w.capacity = 70;
        w.currentStock = 50;
        w.incomingStock = 30;

        assertTrue(w.isOverCapacity());
    }

    @Test
    void overCapacity_false() {
        WarehouseMetricsExtended w = new WarehouseMetricsExtended();
        w.capacity = 200;
        w.currentStock = 50;
        w.incomingStock = 30;

        assertFalse(w.isOverCapacity());
    }
}