package com.fulfilment.application.monolith.warehouses.adapters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseReplenishmentEngineTest {

    @Test
    void shouldReturnInvalidWhenCodeIsNull() {
        var r = DbWarehouseReplenishmentEngine.evaluate(
                null, 50, 10, 100
        );

        assertEquals("INVALID", r.recommendation);
        assertEquals("NONE", r.priority);
    }

    @Test
    void shouldReturnDataMissingWhenAnyFieldNull() {
        var r = DbWarehouseReplenishmentEngine.evaluate(
                "W1", null, 10, 100
        );

        assertEquals("DATA_MISSING", r.recommendation);
        assertEquals("LOW", r.priority);
    }

    @Test
    void shouldReturnInvalidValuesWhenNegativeStock() {
        var r = DbWarehouseReplenishmentEngine.evaluate(
                "W1", -5, 10, 100
        );

        assertEquals("INVALID_VALUES", r.recommendation);
        assertEquals("LOW", r.priority);
    }

    @Test
    void shouldReturnRestockImmediatelyWhenStockBelowMin() {
        var r = DbWarehouseReplenishmentEngine.evaluate(
                "W1", 5, 10, 100
        );

        assertEquals("RESTOCK_IMMEDIATELY", r.recommendation);
        assertEquals("HIGH", r.priority);
    }

    @Test
    void shouldReturnPlanReplenishmentWhenStockModerate() {
        var r = DbWarehouseReplenishmentEngine.evaluate(
                "W1", 15, 10, 100
        );

        assertEquals("PLAN_REPLENISHMENT", r.recommendation);
        assertEquals("MEDIUM", r.priority);
    }

    @Test
    void shouldReturnReduceStockWhenAboveCapacity() {
        var r = DbWarehouseReplenishmentEngine.evaluate(
                "W1", 120, 10, 100
        );

        assertEquals("REDUCE_STOCK", r.recommendation);
        assertEquals("HIGH", r.priority);
    }

    @Test
    void shouldReturnCapacityFullWhenEqualCapacity() {
        var r = DbWarehouseReplenishmentEngine.evaluate(
                "W1", 100, 10, 100
        );

        assertEquals("CAPACITY_FULL", r.recommendation);
        assertEquals("MEDIUM", r.priority);
    }

    @Test
    void shouldReturnStableWhenNormalCondition() {
        var r = DbWarehouseReplenishmentEngine.evaluate(
                "W1", 50, 10, 100
        );

        assertEquals("STABLE", r.recommendation);
        assertEquals("LOW", r.priority);
    }
}