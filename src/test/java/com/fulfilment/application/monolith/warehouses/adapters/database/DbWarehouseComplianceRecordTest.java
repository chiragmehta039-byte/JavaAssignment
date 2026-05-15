package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseComplianceRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseComplianceRecordTest {

    @Test
    void compliant_case() {
        var c = DbWarehouseComplianceRecord.create(
                "W1",
                true,
                true,
                true,
                0
        );

        assertEquals("COMPLIANT", c.complianceStatus);
        assertEquals("LOW", c.riskGrade);
    }

    @Test
    void warning_case() {
        var c = DbWarehouseComplianceRecord.create(
                "W1",
                false,
                true,
                true,
                1
        );

        assertEquals("WARNING", c.complianceStatus);
        assertEquals("MEDIUM", c.riskGrade);
    }

    @Test
    void non_compliant_case() {
        var c = DbWarehouseComplianceRecord.create(
                "W1",
                false,
                false,
                false,
                5
        );

        assertEquals("NON_COMPLIANT", c.complianceStatus);
        assertEquals("HIGH", c.riskGrade);
    }

    @Test
    void invalid_code_case() {
        var c = DbWarehouseComplianceRecord.create(
                null,
                true,
                true,
                true,
                0
        );

        assertEquals("INVALID", c.complianceStatus);
        assertEquals("UNKNOWN", c.riskGrade);
    }

    @Test
    void null_violation_case() {
        var c = DbWarehouseComplianceRecord.create(
                "W1",
                true,
                true,
                true,
                null
        );

        assertEquals("COMPLIANT", c.complianceStatus);
    }
}