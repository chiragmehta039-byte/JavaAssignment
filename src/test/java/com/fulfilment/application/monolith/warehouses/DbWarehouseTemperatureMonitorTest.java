package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseTemperatureMonitor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseTemperatureMonitorTest {

    @Test
    void shouldReturnUnknown() {
        var m = DbWarehouseTemperatureMonitor.create("W1", null);
        assertEquals("UNKNOWN", m.alertLevel);
    }

    @Test
    void criticalTemp() {
        var m = DbWarehouseTemperatureMonitor.create("W1", -20);
        assertEquals("CRITICAL", m.alertLevel);
    }

    @Test
    void lowTemp() {
        var m = DbWarehouseTemperatureMonitor.create("W1", -5);
        assertEquals("LOW", m.alertLevel);
    }

    @Test
    void normalTemp() {
        var m = DbWarehouseTemperatureMonitor.create("W1", 20);
        assertEquals("NORMAL", m.alertLevel);
    }

    @Test
    void highTemp() {
        var m = DbWarehouseTemperatureMonitor.create("W1", 40);
        assertEquals("HIGH", m.alertLevel);
    }
}