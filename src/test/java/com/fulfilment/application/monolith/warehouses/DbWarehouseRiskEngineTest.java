package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseRiskEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseRiskEngineTest {

    @Test
    void shouldReturnUnknownForInvalidCode() {
        var r = DbWarehouseRiskEngine.evaluate(
                null, 100, 200, false, false, 0
        );

        assertEquals("UNKNOWN", r.riskLevel);
        assertEquals("INVALID_INPUT", r.actionRequired);
    }

    @Test
    void shouldReturnUnknownForMissingData() {
        var r = DbWarehouseRiskEngine.evaluate(
                "W1", null, null, false, false, 0
        );

        assertEquals("UNKNOWN", r.riskLevel);
        assertEquals("MISSING_DATA", r.actionRequired);
    }

    @Test
    void shouldReturnLowRisk() {
        var r = DbWarehouseRiskEngine.evaluate(
                "W1", 50, 200, false, false, 0
        );

        assertEquals("LOW", r.riskLevel);
    }

    @Test
    void shouldReturnMediumRisk() {
        var r = DbWarehouseRiskEngine.evaluate(
                "W1", 100, 200, true, false, 1
        );

        assertEquals("MEDIUM", r.riskLevel);
    }

    @Test
    void shouldReturnHighRisk() {
        var r = DbWarehouseRiskEngine.evaluate(
                "W1", 250, 200, true, true, 2
        );

    }

    @Test
    void shouldReturnCriticalRisk() {
        var r = DbWarehouseRiskEngine.evaluate(
                "W1", 300, 200, true, true, 10
        );

        assertEquals("CRITICAL", r.riskLevel);
        assertEquals("IMMEDIATE_SHUTDOWN", r.actionRequired);
    }
}