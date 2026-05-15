package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseMaintenanceLog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseMaintenanceLogTest {

    @Test
    void invalid_code() {
        var l = DbWarehouseMaintenanceLog.create(null, "URGENT", false);
        assertEquals("INVALID", l.status);
    }

    @Test
    void completed_case() {
        var l = DbWarehouseMaintenanceLog.create("W1", "NORMAL", true);
        assertEquals("DONE", l.status);
    }

    @Test
    void urgent_pending() {
        var l = DbWarehouseMaintenanceLog.create("W1", "URGENT", false);
        assertEquals("PENDING_CRITICAL", l.status);
    }

    @Test
    void normal_pending() {
        var l = DbWarehouseMaintenanceLog.create("W1", "NORMAL", false);
        assertEquals("PENDING", l.status);
    }
}