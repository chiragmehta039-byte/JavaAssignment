package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseLog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseLogTest {

    @Test
    void shouldCreateInfoLog() {
        DbWarehouseLog log = DbWarehouseLog.create("system started", "INFO");

        assertEquals("INFO", log.level);
    }

    @Test
    void shouldCreateWarnLog() {
        DbWarehouseLog log = DbWarehouseLog.create("slow response", "WARN");

        assertEquals("WARN", log.level);
    }

    @Test
    void shouldCreateErrorLog() {
        DbWarehouseLog log = DbWarehouseLog.create("failure", "ERROR");

        assertEquals("ERROR", log.level);
    }

    @Test
    void shouldDefaultToErrorWhenMessageBlank() {
        DbWarehouseLog log = DbWarehouseLog.create("", "INFO");

        assertEquals("ERROR", log.level);
    }
}