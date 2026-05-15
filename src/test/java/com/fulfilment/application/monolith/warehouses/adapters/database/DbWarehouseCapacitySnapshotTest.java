package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseCapacitySnapshot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseCapacitySnapshotTest {

    @Test
    void healthy_case() {
        var s = DbWarehouseCapacitySnapshot.create("W1", 100, 30, 10);

        assertEquals("HEALTHY", s.healthStatus);
    }

    @Test
    void warning_case() {
        var s = DbWarehouseCapacitySnapshot.create("W1", 100, 70, 10);

    }

    @Test
    void full_case() {
        var s = DbWarehouseCapacitySnapshot.create("W1", 100, 60, 40);

        assertEquals("FULL", s.healthStatus);
    }

    @Test
    void overloaded_case() {
        var s = DbWarehouseCapacitySnapshot.create("W1", 100, 90, 20);

        assertEquals("OVERLOADED", s.healthStatus);
    }

    @Test
    void invalid_case_null_code() {
        var s = DbWarehouseCapacitySnapshot.create(null, 100, 10, 10);

        assertEquals("INVALID", s.healthStatus);
    }

    @Test
    void invalid_case_null_capacity() {
        var s = DbWarehouseCapacitySnapshot.create("W1", null, 10, 10);

        assertEquals("INVALID", s.healthStatus);
    }
}