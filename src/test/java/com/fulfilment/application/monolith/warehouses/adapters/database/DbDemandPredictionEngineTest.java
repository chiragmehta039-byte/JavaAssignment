package com.fulfilment.application.monolith.warehouses.adapters.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbDemandPredictionEngineTest {

    @Test
    void shouldReturnInvalidWhenCodeNull() {
        var r = DbDemandPredictionEngine.predict(null, 80);
        assertEquals("INVALID", r.prediction);
        assertEquals(90, r.predictedDemand);
    }

    @Test
    void shouldReturnUnknownWhenPastDemandNull() {
        var r = DbDemandPredictionEngine.predict("I1", null);
        assertEquals("UNKNOWN", r.prediction);
        assertEquals(0, r.predictedDemand);
    }

    @Test
    void shouldReturnHighDemand() {
        var r = DbDemandPredictionEngine.predict("I1", 120);
        assertEquals("HIGH_DEMAND", r.prediction);
        assertEquals(130, r.predictedDemand);
    }

    @Test
    void shouldReturnMediumDemand() {
        var r = DbDemandPredictionEngine.predict("I1", 70);
        assertEquals("MEDIUM_DEMAND", r.prediction);
    }

    @Test
    void shouldReturnLowDemand() {
        var r = DbDemandPredictionEngine.predict("I1", 20);
        assertEquals("LOW_DEMAND", r.prediction);
    }
}