package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseDispatchEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseDispatchEngineTest {

    @Test
    void invalid_code() {
        assertEquals("INVALID",
                DbWarehouseDispatchEngine.evaluate(null, 10, 100).dispatchStatus);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN",
                DbWarehouseDispatchEngine.evaluate("W1", null, null).dispatchStatus);
    }

    @Test
    void overloaded() {
        assertEquals("OVERLOADED",
                DbWarehouseDispatchEngine.evaluate("W1", 120, 100).dispatchStatus);
    }

    @Test
    void full_load() {
        assertEquals("FULL_LOAD",
                DbWarehouseDispatchEngine.evaluate("W1", 100, 100).dispatchStatus);
    }

    @Test
    void ready() {
        assertEquals("READY",
                DbWarehouseDispatchEngine.evaluate("W1", 50, 100).dispatchStatus);
    }
}