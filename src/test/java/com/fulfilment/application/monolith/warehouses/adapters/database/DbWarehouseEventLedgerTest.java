package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseEventLedger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseEventLedgerTest {

    @Test
    void create_create_success() {
        var e = DbWarehouseEventLedger.create(
                "W1", "CREATE", "SYS",
                null, 100,
                null, 200,
                false
        );

        assertEquals("SUCCESS", e.status);
    }

    @Test
    void create_invalid_code() {
        var e = DbWarehouseEventLedger.create(
                null, "CREATE", "SYS",
                null, 100,
                null, 200,
                false
        );

        assertEquals("FAILED", e.status);
    }

    @Test
    void update_stock_decrease() {
        var e = DbWarehouseEventLedger.create(
                "W1", "UPDATE", "SYS",
                100, 50,
                null, null,
                false
        );

        assertEquals("PARTIAL", e.status);
    }

    @Test
    void archive_auto() {
        var e = DbWarehouseEventLedger.create(
                "W1", "ARCHIVE", "SYS",
                null, null,
                null, null,
                true
        );

        assertEquals("SUCCESS", e.status);
        assertEquals("AUTO_ARCHIVE", e.remarks);
    }

    @Test
    void move_failure_no_source() {
        var e = DbWarehouseEventLedger.create(
                "W1", "MOVE", null,
                null, null,
                null, null,
                false
        );

        assertEquals("FAILED", e.status);
    }

    @Test
    void unknown_event() {
        var e = DbWarehouseEventLedger.create(
                "W1", "RANDOM", "SYS",
                null, null,
                null, null,
                false
        );

        assertEquals("FAILED", e.status);
    }
}