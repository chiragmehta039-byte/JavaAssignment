package com.fulfilment.application.monolith.warehouses.adapters.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbCapacityForecastEngineTest {

    @Test
    void shouldReturnInvalidWhenZoneNull() {
        var r = DbCapacityForecastEngine.predict(null, 10, 20);
        assertEquals("INVALID", r.forecastStatus);
    }

    @Test
    void shouldReturnUnknownWhenAnyNull() {
        var r = DbCapacityForecastEngine.predict("Z1", null, 20);
        assertEquals("UNKNOWN", r.forecastStatus);
    }

    @Test
    void shouldReturnExpandWhenForecastHigher() {
        var r = DbCapacityForecastEngine.predict("Z1", 10, 20);
        assertEquals("EXPAND", r.forecastStatus);
    }

    @Test
    void shouldReturnReduceWhenForecastLower() {
        var r = DbCapacityForecastEngine.predict("Z1", 20, 10);
        assertEquals("REDUCE", r.forecastStatus);
    }

    @Test
    void shouldReturnStableWhenEqual() {
        var r = DbCapacityForecastEngine.predict("Z1", 10, 10);
        assertEquals("STABLE", r.forecastStatus);
    }
}