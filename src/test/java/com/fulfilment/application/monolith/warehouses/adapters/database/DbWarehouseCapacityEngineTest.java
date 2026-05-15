package com.fulfilment.application.monolith.warehouses.adapters.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DbWarehouseCapacityEngineTest {

    @Test
    void invalid() {
        assertEquals("INVALID",
                DbWarehouseCapacityEngine.compute(null, null, null).status);
    }

    @Test
    void overflow() {
        assertEquals("OVERFLOW",
                DbWarehouseCapacityEngine.compute("W1", 100, 120).status);
    }

    @Test
    void full() {
        assertEquals("FULL",
                DbWarehouseCapacityEngine.compute("W1", 100, 100).status);
    }

    @Test
    void high() {
        assertEquals("HIGH",
                DbWarehouseCapacityEngine.compute("W1", 100, 90).status);
    }

    @Test
    void ok() {
        assertEquals("OK",
                DbWarehouseCapacityEngine.compute("W1", 100, 50).status);
    }
}