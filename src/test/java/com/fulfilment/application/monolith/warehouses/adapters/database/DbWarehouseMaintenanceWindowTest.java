package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseMaintenanceWindow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseMaintenanceWindowTest {

    @Test
    void urgent() {
        assertEquals("URGENT", DbWarehouseMaintenanceWindow.create("EMERGENCY", 2).status);
    }

    @Test
    void longWindow() {
        assertEquals("LONG", DbWarehouseMaintenanceWindow.create("NORMAL", 10).status);
    }

    @Test
    void shortWindow() {
        assertEquals("SHORT", DbWarehouseMaintenanceWindow.create("NORMAL", 2).status);
    }

    @Test
    void invalid() {
        assertEquals("INVALID", DbWarehouseMaintenanceWindow.create("NORMAL", 0).status);
    }
}