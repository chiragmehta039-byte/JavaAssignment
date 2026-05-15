package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.extra.WarehouseRangeFilter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WarehouseRangeFilterTest {

    @Test
    void testFilterRange() {
        Warehouse w1 = new Warehouse();
        w1.capacity = 10;

        Warehouse w2 = new Warehouse();
        w2.capacity = 50;

        assertEquals(1,
                WarehouseRangeFilter.filterByCapacity(
                        java.util.Arrays.asList(w1, w2), 20, 60).size());
    }

    @Test
    void testNullList() {
        assertTrue(WarehouseRangeFilter.filterByCapacity(null, 0, 100).isEmpty());
    }
}