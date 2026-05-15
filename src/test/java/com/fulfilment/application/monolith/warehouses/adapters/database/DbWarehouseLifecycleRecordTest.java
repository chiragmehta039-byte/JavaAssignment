package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseLifecycleRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseLifecycleRecordTest {

    @Test
    void create_success() {
        var r = DbWarehouseLifecycleRecord.create(
                "W1", "CREATE",
                null, 100,
                null, 10,
                false,
                "admin"
        );

        assertEquals("SUCCESS", r.status);
    }

    @Test
    void create_invalid_code() {
        var r = DbWarehouseLifecycleRecord.create(
                null, "CREATE",
                null, 100,
                null, 10,
                false,
                "admin"
        );

        assertEquals("FAILED", r.status);
    }

    @Test
    void update_capacity_reduced() {
        var r = DbWarehouseLifecycleRecord.create(
                "W1", "UPDATE",
                200, 100,
                null, null,
                false,
                "admin"
        );

        assertEquals("PARTIAL", r.status);
    }

    @Test
    void archive_auto() {
        var r = DbWarehouseLifecycleRecord.create(
                "W1", "ARCHIVE",
                null, null,
                null, null,
                true,
                "system"
        );

        assertEquals("SUCCESS", r.status);
    }

    @Test
    void deactivate_partial() {
        var r = DbWarehouseLifecycleRecord.create(
                "W1", "DEACTIVATE",
                null, null,
                null, 10,
                false,
                "admin"
        );

        assertEquals("PARTIAL", r.status);
    }

    @Test
    void unknown_event() {
        var r = DbWarehouseLifecycleRecord.create(
                "W1", "RANDOM",
                null, null,
                null, null,
                false,
                "admin"
        );

        assertEquals("FAILED", r.status);
    }

    @Test
    void null_user_defaults_system() {
        var r = DbWarehouseLifecycleRecord.create(
                "W1", "CREATE",
                null, 100,
                null, 10,
                false,
                null
        );

        assertEquals("SYSTEM", r.performedBy);
    }
}