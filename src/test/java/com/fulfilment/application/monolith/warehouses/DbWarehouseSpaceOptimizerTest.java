package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseSpaceOptimizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseSpaceOptimizerTest {

    @Test
    void invalid_case() {
        var o = DbWarehouseSpaceOptimizer.create(null, null, null);
        assertEquals("INVALID", o.optimizationStatus);
    }

    @Test
    void overutilized() {
        var o = DbWarehouseSpaceOptimizer.create("W1", 100, 150);
        assertEquals("OVERUTILIZED", o.optimizationStatus);
    }

    @Test
    void near_full() {
        var o = DbWarehouseSpaceOptimizer.create("W1", 100, 85);
        assertEquals("NEAR_FULL", o.optimizationStatus);
    }

    @Test
    void optimal() {
        var o = DbWarehouseSpaceOptimizer.create("W1", 100, 40);
        assertEquals("OPTIMAL", o.optimizationStatus);
    }
}