package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseInventorySnapshot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseInventorySnapshotTest {

    @Test
    void shouldReturnMinusOneWhenCodeNull() {
        var r = DbWarehouseInventorySnapshot.build(null, 100, 20);
        assertEquals(-1, r.availableItems);
    }

    @Test
    void shouldReturnZeroWhenTotalOrReservedNull() {
        var r1 = DbWarehouseInventorySnapshot.build("W1", null, 10);
        assertEquals(0, r1.availableItems);

        var r2 = DbWarehouseInventorySnapshot.build("W1", 100, null);
        assertEquals(0, r2.availableItems);
    }

    @Test
    void shouldCalculateAvailableItemsCorrectly() {
        var r = DbWarehouseInventorySnapshot.build("W1", 100, 30);
        assertEquals(70, r.availableItems);
    }
}