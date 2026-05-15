package com.fulfilment.application.monolith.coverage;

import com.fulfilment.application.monolith.warehouses.domain.models.WarehouseMetrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseMetricsTest {

    // 1. Overloaded TRUE
    @Test
    void overloaded_whenStockGreaterThanCapacity() {
        WarehouseMetrics metrics = new WarehouseMetrics();
        metrics.currentStock = 200;
        metrics.availableCapacity = 100;

        assertTrue(metrics.isOverloaded());
    }

    // 2. Overloaded FALSE
    @Test
    void overloaded_whenStockLessThanCapacity() {
        WarehouseMetrics metrics = new WarehouseMetrics();
        metrics.currentStock = 50;
        metrics.availableCapacity = 100;

        assertFalse(metrics.isOverloaded());
    }

    // 3. Overloaded EQUAL case
    @Test
    void overloaded_whenStockEqualsCapacity() {
        WarehouseMetrics metrics = new WarehouseMetrics();
        metrics.currentStock = 100;
        metrics.availableCapacity = 100;

        assertFalse(metrics.isOverloaded());
    }

    // 4. Overloaded NULL stock
    @Test
    void overloaded_whenStockNull() {
        WarehouseMetrics metrics = new WarehouseMetrics();
        metrics.availableCapacity = 100;

        assertFalse(metrics.isOverloaded());
    }

    // 5. Overloaded NULL capacity
    @Test
    void overloaded_whenCapacityNull() {
        WarehouseMetrics metrics = new WarehouseMetrics();
        metrics.currentStock = 100;

        assertFalse(metrics.isOverloaded());
    }

    // 6. Overloaded BOTH NULL
    @Test
    void overloaded_whenBothNull() {
        WarehouseMetrics metrics = new WarehouseMetrics();

        assertFalse(metrics.isOverloaded());
    }

    // 7. needsAttention TRUE (overCapacity)
    @Test
    void needsAttention_whenOverCapacityTrue() {
        WarehouseMetrics metrics = new WarehouseMetrics();
        metrics.overCapacity = true;

        assertTrue(metrics.needsAttention());
    }

    // 8. needsAttention TRUE (safetyBreached)
    @Test
    void needsAttention_whenSafetyBreachedTrue() {
        WarehouseMetrics metrics = new WarehouseMetrics();
        metrics.safetyBreached = true;

        assertTrue(metrics.needsAttention());
    }

    // 9. needsAttention TRUE (both true)
    @Test
    void needsAttention_whenBothTrue() {
        WarehouseMetrics metrics = new WarehouseMetrics();
        metrics.overCapacity = true;
        metrics.safetyBreached = true;

        assertTrue(metrics.needsAttention());
    }

    // 10. needsAttention FALSE
    @Test
    void needsAttention_whenAllFalse() {
        WarehouseMetrics metrics = new WarehouseMetrics();
        metrics.overCapacity = false;
        metrics.safetyBreached = false;

        assertFalse(metrics.needsAttention());
    }

    // 11. needsAttention NULL safe
    @Test
    void needsAttention_whenNullValues() {
        WarehouseMetrics metrics = new WarehouseMetrics();

        assertFalse(metrics.needsAttention());
    }

    // 12. mixed scenario
    @Test
    void mixedScenario_randomValues() {
        WarehouseMetrics metrics = new WarehouseMetrics();
        metrics.currentStock = 80;
        metrics.availableCapacity = 100;
        metrics.overCapacity = false;
        metrics.safetyBreached = true;

        assertFalse(metrics.isOverloaded());
        assertTrue(metrics.needsAttention());
    }
}