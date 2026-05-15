package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseCapacityAudit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseCapacityAuditTest {

    @Test
    void ok() {
        assertEquals("OK", DbWarehouseCapacityAudit.create(100, 50).flag);
    }

    @Test
    void full() {
        assertEquals("FULL", DbWarehouseCapacityAudit.create(100, 100).flag);
    }

    @Test
    void over() {
        assertEquals("OVER", DbWarehouseCapacityAudit.create(100, 150).flag);
    }

    @Test
    void invalid() {
        assertEquals("INVALID", DbWarehouseCapacityAudit.create(null, 10).flag);
    }
}