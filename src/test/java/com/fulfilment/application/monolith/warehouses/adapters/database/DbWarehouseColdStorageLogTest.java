package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseColdStorageLog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseColdStorageLogTest {

    @Test
    void normal() {
        assertEquals("NORMAL", DbWarehouseColdStorageLog.create(5).status);
    }

    @Test
    void low() {
        assertEquals("LOW", DbWarehouseColdStorageLog.create(-5).status);
    }

    @Test
    void critical() {
        assertEquals("CRITICAL", DbWarehouseColdStorageLog.create(-20).status);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehouseColdStorageLog.create(null).status);
    }
}