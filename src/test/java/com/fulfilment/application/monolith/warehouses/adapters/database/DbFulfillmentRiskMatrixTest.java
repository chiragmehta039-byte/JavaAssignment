package com.fulfilment.application.monolith.warehouses.adapters.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbFulfillmentRiskMatrixTest {

    @Test
    void shouldReturnInvalidWhenCodeNullOrBlank() {
        var r1 = DbFulfillmentRiskMatrix.evaluate(null, 3, true);
        assertEquals("INVALID", r1.risk);

        var r2 = DbFulfillmentRiskMatrix.evaluate("   ", 3, true);
        assertEquals("INVALID", r2.risk);
    }

    @Test
    void shouldReturnCriticalWhenStockIssueAndHighDelay() {
        var r = DbFulfillmentRiskMatrix.evaluate("E1", 10, true);
        assertEquals("CRITICAL", r.risk);
    }

    @Test
    void shouldReturnHighWhenOnlyStockIssue() {
        var r = DbFulfillmentRiskMatrix.evaluate("E1", 3, true);
        assertEquals("HIGH", r.risk);
    }

    @Test
    void shouldReturnMediumWhenOnlyDelayHigh() {
        var r = DbFulfillmentRiskMatrix.evaluate("E1", 10, false);
        assertEquals("MEDIUM", r.risk);
    }

    @Test
    void shouldReturnLowWhenNoIssues() {
        var r = DbFulfillmentRiskMatrix.evaluate("E1", 2, false);
        assertEquals("LOW", r.risk);
    }

    @Test
    void shouldReturnLowWhenDelayNullAndNoIssue() {
        var r = DbFulfillmentRiskMatrix.evaluate("E1", null, false);
        assertEquals("LOW", r.risk);
    }
}