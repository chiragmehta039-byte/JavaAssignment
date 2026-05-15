package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseSecurityAlert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseSecurityAlertTest {

    @Test
    void critical() {
        assertEquals("CRITICAL", DbWarehouseSecurityAlert.create(true, true).level);
    }

    @Test
    void high() {
        assertEquals("HIGH", DbWarehouseSecurityAlert.create(true, false).level);
    }

    @Test
    void medium() {
        assertEquals("MEDIUM", DbWarehouseSecurityAlert.create(false, true).level);
    }

    @Test
    void low() {
        assertEquals("LOW", DbWarehouseSecurityAlert.create(false, false).level);
    }
}