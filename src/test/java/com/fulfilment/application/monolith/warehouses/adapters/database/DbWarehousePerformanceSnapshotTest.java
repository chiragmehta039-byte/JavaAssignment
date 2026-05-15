package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehousePerformanceSnapshot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehousePerformanceSnapshotTest {

    @Test
    void normalSnapshot() {
        DbWarehousePerformanceSnapshot s =
                DbWarehousePerformanceSnapshot.create(100, 5);

        assertEquals(100, s.throughput);
    }

    @Test
    void errorSnapshot() {
        DbWarehousePerformanceSnapshot s =
                DbWarehousePerformanceSnapshot.create(100, 20);

        assertEquals(0, s.throughput);
    }
}