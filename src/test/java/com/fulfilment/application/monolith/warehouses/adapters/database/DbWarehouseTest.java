package com.fulfilment.application.monolith.warehouses.adapters.database;

import static org.junit.jupiter.api.Assertions.*;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DbWarehouseTest {

    @Test
    void shouldConvertDbWarehouseToWarehouse() {

        LocalDateTime now = LocalDateTime.now();

        DbWarehouse db = new DbWarehouse();
        db.businessUnitCode = "WH-001";
        db.location = "Delhi";
        db.capacity = 100;
        db.stock = 50;
        db.createdAt = now;
        db.archivedAt = null;

        Warehouse w = db.toWarehouse();

        assertAll(
                () -> assertNotNull(w),
                () -> assertEquals("WH-001", w.businessUnitCode),
                () -> assertEquals("Delhi", w.location),
                () -> assertEquals(100, w.capacity),
                () -> assertEquals(50, w.stock),
                () -> assertEquals(now, w.createdAt),
                () -> assertNull(w.archivedAt)
        );
    }

    @Test
    void shouldConvertArchivedWarehouseCorrectly() {

        DbWarehouse db = new DbWarehouse();
        db.businessUnitCode = "WH-002";
        db.location = "Mumbai";
        db.capacity = 200;
        db.stock = 30;
        db.createdAt = LocalDateTime.now();
        db.archivedAt = LocalDateTime.now();

        Warehouse w = db.toWarehouse();

        assertNotNull(w);
        assertNotNull(w.archivedAt);
    }

    @Test
    void shouldHandleZeroCapacityAndStock() {

        DbWarehouse db = new DbWarehouse();
        db.businessUnitCode = "WH-003";
        db.location = "Pune";
        db.capacity = 0;
        db.stock = 0;
        db.createdAt = LocalDateTime.now();

        Warehouse w = db.toWarehouse();

        assertEquals(0, w.capacity);
        assertEquals(0, w.stock);
    }

    @Test
    void shouldHandleNullOptionalFields() {

        DbWarehouse db = new DbWarehouse();
        db.businessUnitCode = "WH-004";
        db.location = "Noida";
        db.capacity = 10;
        db.stock = 5;

        Warehouse w = db.toWarehouse();

        assertNotNull(w);
        assertNull(w.archivedAt);
    }
}