package com.fulfilment.application.monolith.warehouses.adapters.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbMovementTrackingEngineTest {

    @Test
    void shouldReturnInvalidWhenCodeNull() {
        var r = DbMovementTrackingEngine.track(null, 10, 5);
        assertEquals("INVALID", r.movementStatus);
    }

    @Test
    void shouldReturnPartialWhenAnyNull() {
        var r = DbMovementTrackingEngine.track("M1", null, 5);
        assertEquals("PARTIAL", r.movementStatus);
    }

    @Test
    void shouldReturnPositiveWhenInGreaterThanOut() {
        var r = DbMovementTrackingEngine.track("M1", 20, 5);
        assertEquals("POSITIVE", r.movementStatus);
    }

    @Test
    void shouldReturnNegativeWhenOutGreaterThanIn() {
        var r = DbMovementTrackingEngine.track("M1", 5, 20);
        assertEquals("NEGATIVE", r.movementStatus);
    }

    @Test
    void shouldReturnBalancedWhenEqual() {
        var r = DbMovementTrackingEngine.track("M1", 10, 10);
        assertEquals("BALANCED", r.movementStatus);
    }
}