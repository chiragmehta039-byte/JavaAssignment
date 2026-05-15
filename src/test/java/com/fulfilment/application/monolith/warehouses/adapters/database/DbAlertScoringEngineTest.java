package com.fulfilment.application.monolith.warehouses.adapters.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbAlertScoringEngineTest {

    @Test
    void shouldReturnInvalidWhenCodeNull() {
        var r = DbAlertScoringEngine.evaluate(null, 90);
        assertEquals("INVALID", r.alert);
    }

    @Test
    void shouldReturnUnknownWhenScoreNull() {
        var r = DbAlertScoringEngine.evaluate("A1", null);
        assertEquals("UNKNOWN", r.alert);
    }

    @Test
    void shouldReturnCritical() {
        var r = DbAlertScoringEngine.evaluate("A1", 90);
        assertEquals("CRITICAL", r.alert);
    }

    @Test
    void shouldReturnHigh() {
        var r = DbAlertScoringEngine.evaluate("A1", 60);
        assertEquals("HIGH", r.alert);
    }

    @Test
    void shouldReturnMedium() {
        var r = DbAlertScoringEngine.evaluate("A1", 30);
        assertEquals("MEDIUM", r.alert);
    }

    @Test
    void shouldReturnLow() {
        var r = DbAlertScoringEngine.evaluate("A1", 10);
        assertEquals("LOW", r.alert);
    }
}