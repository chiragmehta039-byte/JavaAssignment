package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseInspectionReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseInspectionReportTest {

    @Test
    void passed() {
        assertEquals("PASSED", DbWarehouseInspectionReport.create(true, 1).result);
    }

    @Test
    void failed() {
        assertEquals("FAILED", DbWarehouseInspectionReport.create(false, 10).result);
    }

    @Test
    void review() {
        assertEquals("REVIEW", DbWarehouseInspectionReport.create(false, 2).result);
    }
}