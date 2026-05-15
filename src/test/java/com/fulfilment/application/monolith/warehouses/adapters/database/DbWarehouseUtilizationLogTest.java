package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseUtilizationLog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseUtilizationLogTest {

    @Test
    void ok() {
        assertEquals("OK", DbWarehouseUtilizationLog.create(50, 100).level);
    }

    @Test
    void full() {
        assertEquals("FULL", DbWarehouseUtilizationLog.create(100, 100).level);
    }

    @Test
    void over() {
        assertEquals("OVER", DbWarehouseUtilizationLog.create(150, 100).level);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehouseUtilizationLog.create(null, 100).level);
    }
}