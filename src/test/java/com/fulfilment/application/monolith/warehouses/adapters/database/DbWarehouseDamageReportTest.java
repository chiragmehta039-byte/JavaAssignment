package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseDamageReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseDamageReportTest {

    @Test
    void severe() {
        assertEquals("SEVERE", DbWarehouseDamageReport.create(90).category);
    }

    @Test
    void moderate() {
        assertEquals("MODERATE", DbWarehouseDamageReport.create(50).category);
    }

    @Test
    void minor() {
        assertEquals("MINOR", DbWarehouseDamageReport.create(10).category);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehouseDamageReport.create(null).category);
    }
}