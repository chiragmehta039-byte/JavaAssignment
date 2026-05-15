package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseSlotAllocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseSlotAllocationTest {

    @Test
    void available() {
        assertEquals("AVAILABLE", DbWarehouseSlotAllocation.create("S1", 100, 50).status);
    }

    @Test
    void full() {
        assertEquals("FULL", DbWarehouseSlotAllocation.create("S1", 100, 100).status);
    }

    @Test
    void overflow() {
        assertEquals("OVERFLOW", DbWarehouseSlotAllocation.create("S1", 100, 150).status);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehouseSlotAllocation.create("S1", null, 10).status);
    }
}