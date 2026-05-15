package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouse;
import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseTest {

    @Test
    void shouldMapEntityToDomainCorrectly() {

        DbWarehouse entity = new DbWarehouse();
        entity.businessUnitCode = "WH-001";
        entity.location = "DELHI";
        entity.capacity = 100;
        entity.stock = 50;
        entity.createdAt = LocalDateTime.now();
        entity.archivedAt = LocalDateTime.now();

        Warehouse w = entity.toWarehouse();

        assertEquals("WH-001", w.businessUnitCode);
        assertEquals("DELHI", w.location);
        assertEquals(100, w.capacity);
        assertEquals(50, w.stock);
        assertNotNull(w.createdAt);
        assertNotNull(w.archivedAt);
    }

    @Test
    void shouldHandleNullValuesInMapping() {

        DbWarehouse entity = new DbWarehouse();

        Warehouse w = entity.toWarehouse();

        assertNull(w.businessUnitCode);
        assertNull(w.location);
        assertNull(w.capacity);
        assertNull(w.stock);
    }

    @Test
    void shouldAllowConstructorCreation() {

        DbWarehouse entity = new DbWarehouse();

        assertNotNull(entity);
    }
}