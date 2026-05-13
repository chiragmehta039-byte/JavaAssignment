package com.fulfilment.application.monolith.warehouses.adapters.database;

import static org.junit.jupiter.api.Assertions.*;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DbWarehouseTest {

    @Test
    void shouldConvertDbWarehouseToWarehouse() {

        LocalDateTime now = LocalDateTime.now();

        DbWarehouse dbWarehouse = new DbWarehouse();
        dbWarehouse.businessUnitCode = "WH-001";
        dbWarehouse.location = "Delhi";
        dbWarehouse.capacity = 100;
        dbWarehouse.stock = 50;
        dbWarehouse.createdAt = now;
        dbWarehouse.archivedAt = null;

        Warehouse warehouse = dbWarehouse.toWarehouse();

        assertNotNull(warehouse);

        assertEquals("WH-001", warehouse.businessUnitCode);
        assertEquals("Delhi", warehouse.location);
        assertEquals(100, warehouse.capacity);
        assertEquals(50, warehouse.stock);
        assertEquals(now, warehouse.createdAt);
        assertNull(warehouse.archivedAt);
    }
}