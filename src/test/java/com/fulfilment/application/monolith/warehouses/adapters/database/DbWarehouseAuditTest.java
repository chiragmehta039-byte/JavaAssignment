package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseAudit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseAuditTest {

    // 1. CREATE action branch
    @Test
    void shouldCreateAuditForCreateAction() {
        DbWarehouseAudit audit = DbWarehouseAudit.create("BU-1", "CREATE", "system");

        assertEquals("BU-1", audit.businessUnitCode);
        assertEquals("CREATE", audit.action);
        assertEquals("system", audit.performedBy);
        assertEquals("Warehouse created", audit.remarks);
        assertNotNull(audit.performedAt);
    }

    // 2. UPDATE action branch
    @Test
    void shouldCreateAuditForUpdateAction() {
        DbWarehouseAudit audit = DbWarehouseAudit.create("BU-2", "UPDATE", "admin");

        assertEquals("Warehouse updated", audit.remarks);
    }

    // 3. ARCHIVE action branch
    @Test
    void shouldCreateAuditForArchiveAction() {
        DbWarehouseAudit audit = DbWarehouseAudit.create("BU-3", "ARCHIVE", "system");

        assertEquals("Warehouse archived", audit.remarks);
    }

    // 4. NULL action branch
    @Test
    void shouldHandleNullAction() {
        DbWarehouseAudit audit = DbWarehouseAudit.create("BU-4", null, "system");

        assertEquals("UNKNOWN_ACTION", audit.remarks);
    }

    // 5. EMPTY action branch
    @Test
    void shouldHandleBlankAction() {
        DbWarehouseAudit audit = DbWarehouseAudit.create("BU-5", "", "system");

        assertEquals("UNKNOWN_ACTION", audit.remarks);
    }

    // 6. DEFAULT branch (unknown action)
    @Test
    void shouldHandleUnknownAction() {
        DbWarehouseAudit audit = DbWarehouseAudit.create("BU-6", "DELETE", "system");

        assertEquals("Warehouse updated", audit.remarks);
    }

    // 7. null user handling (no logic but coverage boost)
    @Test
    void shouldAllowNullUser() {
        DbWarehouseAudit audit = DbWarehouseAudit.create("BU-7", "CREATE", null);

        assertNull(audit.performedBy);
        assertEquals("Warehouse created", audit.remarks);
    }
}