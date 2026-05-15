package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseCapacityAlert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseCapacityAlertTest {

    @Test
    void safeCase() {
        assertEquals("SAFE", DbWarehouseCapacityAlert.create(100, 50).level);
    }

    @Test
    void fullCase() {
        assertEquals("FULL", DbWarehouseCapacityAlert.create(100, 100).level);
    }

    @Test
    void criticalCase() {
        assertEquals("CRITICAL", DbWarehouseCapacityAlert.create(100, 150).level);
    }

    @Test
    void unknownCase() {
        assertEquals("UNKNOWN", DbWarehouseCapacityAlert.create(null, 10).level);
    }
}