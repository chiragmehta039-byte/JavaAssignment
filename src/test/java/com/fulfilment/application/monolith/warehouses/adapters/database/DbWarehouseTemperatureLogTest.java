package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseTemperatureLog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseTemperatureLogTest {

    @Test
    void normal() {
        assertEquals("NORMAL", DbWarehouseTemperatureLog.create(20).status);
    }

    @Test
    void hot() {
        assertEquals("HOT", DbWarehouseTemperatureLog.create(40).status);
    }

    @Test
    void freezing() {
        assertEquals("FREEZING", DbWarehouseTemperatureLog.create(-5).status);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehouseTemperatureLog.create(null).status);
    }
}