package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseHealthMonitor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DbWarehouseHealthMonitorTest {

    @Test
    void invalid() {
        var h = DbWarehouseHealthMonitor.evaluate(null, 10, 10);
        assertEquals("INVALID", h.healthStatus);
    }

    @Test
    void unknown() {
        var h = DbWarehouseHealthMonitor.evaluate("W1", null, null);
        assertEquals("UNKNOWN", h.healthStatus);
    }

    @Test
    void critical() {
        var h = DbWarehouseHealthMonitor.evaluate("W1", 50, 90);
        assertEquals("CRITICAL", h.healthStatus);
    }

    @Test
    void warning() {
        var h = DbWarehouseHealthMonitor.evaluate("W1", 35, 70);
        assertEquals("WARNING", h.healthStatus);
    }

    @Test
    void normal() {
        var h = DbWarehouseHealthMonitor.evaluate("W1", 20, 40);
        assertEquals("NORMAL", h.healthStatus);
    }
}