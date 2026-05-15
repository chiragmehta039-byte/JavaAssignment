package com.fulfilment.application.monolith.warehouses.adapters.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DbWarehouseIncidentManagerTest {

    @Test
    void unknown() {
        assertEquals("UNKNOWN",
                DbWarehouseIncidentManager.classify(null, null).severity);
    }

    @Test
    void critical() {
        assertEquals("CRITICAL",
                DbWarehouseIncidentManager.classify("W1", 90).severity);
    }

    @Test
    void high() {
        assertEquals("HIGH",
                DbWarehouseIncidentManager.classify("W1", 60).severity);
    }

    @Test
    void medium() {
        assertEquals("MEDIUM",
                DbWarehouseIncidentManager.classify("W1", 30).severity);
    }

    @Test
    void low() {
        assertEquals("LOW",
                DbWarehouseIncidentManager.classify("W1", 10).severity);
    }
}