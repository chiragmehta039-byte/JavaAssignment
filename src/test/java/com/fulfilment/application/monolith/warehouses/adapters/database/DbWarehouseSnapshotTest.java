package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseSnapshot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseSnapshotTest {

    @Test
    void shouldBeHealthy() {
        DbWarehouseSnapshot s = DbWarehouseSnapshot.create("B1", 50, 100);

        assertEquals("HEALTHY", s.state);
    }

    @Test
    void shouldBeWarning() {
        DbWarehouseSnapshot s = DbWarehouseSnapshot.create("B1", 150, 100);

        assertEquals("WARNING", s.state);
    }

    @Test
    void shouldBeCriticalWhenNullStock() {
        DbWarehouseSnapshot s = DbWarehouseSnapshot.create("B1", null, 100);

        assertEquals("CRITICAL", s.state);
    }

    @Test
    void shouldBeCriticalWhenNullCapacity() {
        DbWarehouseSnapshot s = DbWarehouseSnapshot.create("B1", 50, null);

        assertEquals("CRITICAL", s.state);
    }
}