package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseIncidentReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseIncidentReportTest {

    @Test
    void critical() {
        assertEquals("CRITICAL", DbWarehouseIncidentReport.create("FIRE", 90).outcome);
    }

    @Test
    void major() {
        assertEquals("MAJOR", DbWarehouseIncidentReport.create("THEFT", 50).outcome);
    }

    @Test
    void minor() {
        assertEquals("MINOR", DbWarehouseIncidentReport.create("DELAY", 10).outcome);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehouseIncidentReport.create("DELAY", null).outcome);
    }
}