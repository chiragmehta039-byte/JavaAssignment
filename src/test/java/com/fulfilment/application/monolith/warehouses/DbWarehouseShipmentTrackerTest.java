package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseShipmentTracker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseShipmentTrackerTest {

    @Test
    void shouldCreateInvalidWhenNull() {
        var t = DbWarehouseShipmentTracker.create(null, null, "IN_TRANSIT");
        assertEquals("INVALID", t.status);
    }

    @Test
    void shouldReturnMoving() {
        var t = DbWarehouseShipmentTracker.create("S1", "W1", "IN_TRANSIT");
        assertEquals("MOVING", t.status);
    }

    @Test
    void shouldReturnCompleted() {
        var t = DbWarehouseShipmentTracker.create("S1", "W1", "DELIVERED");
        assertEquals("COMPLETED", t.status);
    }

    @Test
    void shouldReturnError() {
        var t = DbWarehouseShipmentTracker.create("S1", "W1", "FAILED");
        assertEquals("ERROR", t.status);
    }

    @Test
    void shouldReturnPending() {
        var t = DbWarehouseShipmentTracker.create("S1", "W1", "UNKNOWN");
        assertEquals("PENDING", t.status);
    }
}