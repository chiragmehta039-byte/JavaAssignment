package com.fulfilment.application.monolith.warehouses.model;

import com.fulfilment.application.monolith.warehouses.domain.models.WarehouseAuditInfo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseAuditInfoTest {

    @Test
    void shouldNeedInspectionWhenNull() {
        WarehouseAuditInfo a = new WarehouseAuditInfo();
        assertTrue(a.needsInspection());
    }

    @Test
    void shouldBeCompliant() {
        WarehouseAuditInfo a = new WarehouseAuditInfo();
        a.compliancePassed = true;

        assertTrue(a.isCompliant());
    }

    @Test
    void shouldBeNonCompliantWhenFalse() {
        WarehouseAuditInfo a = new WarehouseAuditInfo();
        a.compliancePassed = false;

        assertFalse(a.isCompliant());
    }

    @Test
    void shouldDetectRecentInspection() {
        WarehouseAuditInfo a = new WarehouseAuditInfo();
        a.lastInspection = LocalDateTime.now();

        assertFalse(a.needsInspection());
    }
}