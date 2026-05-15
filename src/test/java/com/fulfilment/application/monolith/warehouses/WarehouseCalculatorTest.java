package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.extra.WarehouseCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WarehouseCalculatorTest {

    @Test
    void testFreeCapacity() {
        Warehouse w = new Warehouse();
        w.capacity = 100;
        w.stock = 40;

        assertEquals(60, WarehouseCalculator.freeCapacity(w));
    }

    @Test
    void testFreeCapacityNull() {
        assertEquals(0, WarehouseCalculator.freeCapacity(null));
    }

    @Test
    void testTotalCapacity() {
        Warehouse w1 = new Warehouse();
        w1.capacity = 50;

        Warehouse w2 = new Warehouse();
        w2.capacity = 30;

        assertEquals(80, WarehouseCalculator.totalCapacity(java.util.Arrays.asList(w1, w2)));
    }

    @Test
    void testTotalCapacityNull() {
        assertEquals(0, WarehouseCalculator.totalCapacity(null));
    }
}