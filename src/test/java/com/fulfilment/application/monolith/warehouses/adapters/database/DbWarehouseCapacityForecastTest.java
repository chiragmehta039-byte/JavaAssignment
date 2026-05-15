package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseCapacityForecast;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseCapacityForecastTest {

    @Test
    void safe() {
        assertEquals("SAFE", DbWarehouseCapacityForecast.create(50, 100).riskLevel);
    }

    @Test
    void high() {
        assertEquals("HIGH", DbWarehouseCapacityForecast.create(150, 100).riskLevel);
    }

    @Test
    void critical() {
        assertEquals("CRITICAL", DbWarehouseCapacityForecast.create(300, 100).riskLevel);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehouseCapacityForecast.create(null, 100).riskLevel);
    }
}