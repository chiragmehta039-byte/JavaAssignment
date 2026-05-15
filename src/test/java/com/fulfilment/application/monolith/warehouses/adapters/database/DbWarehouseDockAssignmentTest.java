package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseDockAssignment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseDockAssignmentTest {

    @Test
    void busy() {
        assertEquals("BUSY", DbWarehouseDockAssignment.create("D1","W1",true).status);
    }

    @Test
    void free() {
        assertEquals("FREE", DbWarehouseDockAssignment.create("D1","W1",false).status);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehouseDockAssignment.create("D1","W1",null).status);
    }
}