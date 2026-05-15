package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehousePriorityEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehousePriorityEngineTest {

    @Test
    void invalid() {
        assertEquals("INVALID",
                DbWarehousePriorityEngine.evaluate(null, 90).priorityLevel);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN",
                DbWarehousePriorityEngine.evaluate("W1", null).priorityLevel);
    }

    @Test
    void urgent() {
        assertEquals("URGENT",
                DbWarehousePriorityEngine.evaluate("W1", 90).priorityLevel);
    }

    @Test
    void high() {
        assertEquals("HIGH",
                DbWarehousePriorityEngine.evaluate("W1", 60).priorityLevel);
    }

    @Test
    void medium() {
        assertEquals("MEDIUM",
                DbWarehousePriorityEngine.evaluate("W1", 30).priorityLevel);
    }

    @Test
    void low() {
        assertEquals("LOW",
                DbWarehousePriorityEngine.evaluate("W1", 10).priorityLevel);
    }
}