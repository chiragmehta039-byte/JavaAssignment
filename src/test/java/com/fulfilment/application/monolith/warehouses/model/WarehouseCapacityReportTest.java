package com.fulfilment.application.monolith.warehouses.model;

import com.fulfilment.application.monolith.warehouses.domain.models.WarehouseCapacityReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseCapacityReportTest {

    @Test
    void shouldBeOverUtilized() {
        WarehouseCapacityReport r = new WarehouseCapacityReport();
        r.maxCapacity = 100;
        r.usedCapacity = 120;

        assertTrue(r.isOverUtilized());
    }

    @Test
    void shouldNotBeOverUtilized() {
        WarehouseCapacityReport r = new WarehouseCapacityReport();
        r.maxCapacity = 100;
        r.usedCapacity = 80;

        assertFalse(r.isOverUtilized());
    }

    @Test
    void shouldDetectFreeSpace() {
        WarehouseCapacityReport r = new WarehouseCapacityReport();
        r.maxCapacity = 100;
        r.usedCapacity = 50;

        assertTrue(r.hasFreeSpace());
    }

    @Test
    void shouldHandleNullValues() {
        WarehouseCapacityReport r = new WarehouseCapacityReport();
        assertFalse(r.isOverUtilized());
        assertFalse(r.hasFreeSpace());
    }
}