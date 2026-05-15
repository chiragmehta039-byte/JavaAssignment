package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseReturnEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseReturnEngineTest {

    @Test
    void invalid() {
        assertEquals("INVALID",
                DbWarehouseReturnEngine.process(null, 10).returnStatus);
    }

    @Test
    void no_data() {
        assertEquals("NO_DATA",
                DbWarehouseReturnEngine.process("W1", null).returnStatus);
    }

    @Test
    void clean() {
        assertEquals("CLEAN",
                DbWarehouseReturnEngine.process("W1", 0).returnStatus);
    }

    @Test
    void low_return() {
        assertEquals("LOW_RETURN",
                DbWarehouseReturnEngine.process("W1", 3).returnStatus);
    }

    @Test
    void high_return() {
        assertEquals("HIGH_RETURN",
                DbWarehouseReturnEngine.process("W1", 10).returnStatus);
    }
}