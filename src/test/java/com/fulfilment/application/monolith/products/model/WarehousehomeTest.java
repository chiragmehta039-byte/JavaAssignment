package com.fulfilment.application.monolith.products.model;



import com.fulfilment.application.monolith.products.model.Warehousehome;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WarehousehomeTest {

    @Test
    void shouldCreateWarehousehomeSuccessfully() {
        Warehousehome w = new Warehousehome();
        w.businessUnitCode = "BU-1";
        w.location = "ZWOLLE-001";
        w.capacity = 100;
        w.stock = 50;

        assertEquals("BU-1", w.businessUnitCode);
        assertEquals("ZWOLLE-001", w.location);
        assertEquals(100, w.capacity);
        assertEquals(50, w.stock);
    }

    @Test
    void shouldAllowNullValues() {
        Warehousehome w = new Warehousehome();

        assertNull(w.businessUnitCode);
        assertNull(w.location);
        assertNull(w.capacity);
        assertNull(w.stock);
        assertNull(w.createdAt);
        assertNull(w.archivedAt);
    }

    @Test
    void shouldSetTimestamps() {
        Warehousehome w = new Warehousehome();
        w.createdAt = LocalDateTime.now();
        w.archivedAt = LocalDateTime.now();

        assertNotNull(w.createdAt);
        assertNotNull(w.archivedAt);
    }

    @Test
    void shouldHandleEdgeValues() {
        Warehousehome w = new Warehousehome();
        w.capacity = Integer.MAX_VALUE;
        w.stock = 0;

        assertTrue(w.capacity > w.stock);
    }
}