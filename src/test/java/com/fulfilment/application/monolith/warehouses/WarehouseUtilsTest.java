package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.util.WarehouseUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseUtilsTest {

    private Warehouse create(String code, String loc, Integer cap, int stock) {
        Warehouse w = new Warehouse();
        w.businessUnitCode = code;
        w.location = loc;
        w.capacity = cap;
        w.stock = stock;
        return w;
    }

    // ---------------- VALIDATION ----------------
    @Test
    void testValidWarehouse() {
        Warehouse w = create("W1", "DELHI", 10, 5);
        assertTrue(WarehouseUtils.isValidWarehouse(w));
    }

    @Test
    void testInvalidWarehouse_null() {
        assertFalse(WarehouseUtils.isValidWarehouse(null));
    }

    @Test
    void testInvalidWarehouse_missingFields() {
        Warehouse w = new Warehouse();
        assertFalse(WarehouseUtils.isValidWarehouse(w));
    }

    // ---------------- FILTER ----------------
    @Test
    void testFilterActive() {
        Warehouse active = create("W1", "DELHI", 10, 5);
        Warehouse archived = create("W2", "MUMBAI", 20, 10);
        archived.archivedAt = java.time.LocalDateTime.now();

        List<Warehouse> result = WarehouseUtils.filterActive(List.of(active, archived));

        assertEquals(1, result.size());
    }

    @Test
    void testFilterActive_nullList() {
        assertTrue(WarehouseUtils.filterActive(null).isEmpty());
    }

    // ---------------- FIND ----------------
    @Test
    void testFindByCode_found() {
        Warehouse w = create("W1", "DELHI", 10, 5);
        assertNotNull(WarehouseUtils.findByCode(List.of(w), "W1"));
    }

    @Test
    void testFindByCode_notFound() {
        assertNull(WarehouseUtils.findByCode(List.of(), "W1"));
    }

    @Test
    void testFindByCode_nullInput() {
        assertNull(WarehouseUtils.findByCode(null, "W1"));
        assertNull(WarehouseUtils.findByCode(List.of(), null));
    }

    // ---------------- UPDATE ----------------
    @Test
    void testSafeUpdate() {
        Warehouse target = create("W1", "DELHI", 10, 5);
        Warehouse source = create("W2", "MUMBAI", 20, 50);

        WarehouseUtils.safeUpdate(target, source);

        assertEquals("MUMBAI", target.location);
        assertEquals(20, target.capacity);
        assertEquals(50, target.stock);
    }

    @Test
    void testSafeUpdate_nullThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> WarehouseUtils.safeUpdate(null, new Warehouse()));

        assertThrows(IllegalArgumentException.class,
                () -> WarehouseUtils.safeUpdate(new Warehouse(), null));
    }

    // ---------------- SORT ----------------
    @Test
    void testSortByCapacityAsc() {
        Warehouse w1 = create("W1", "A", 50, 5);
        Warehouse w2 = create("W2", "B", 10, 10);

        List<Warehouse> result = WarehouseUtils.sort(List.of(w1, w2), "capacity", false);

        assertTrue(result.get(0).capacity <= result.get(1).capacity);
    }

    @Test
    void testSortByCapacityDesc() {
        Warehouse w1 = create("W1", "A", 10, 5);
        Warehouse w2 = create("W2", "B", 50, 10);

        List<Warehouse> result = WarehouseUtils.sort(List.of(w1, w2), "capacity", true);

        assertTrue(result.get(0).capacity >= result.get(1).capacity);
    }

    @Test
    void testSortDefaultField() {
        Warehouse w1 = create("B", "A", 10, 5);
        Warehouse w2 = create("A", "B", 20, 10);

        List<Warehouse> result = WarehouseUtils.sort(List.of(w1, w2), "invalid", false);

        assertEquals("A", result.get(0).businessUnitCode);
    }

    @Test
    void testSortNullList() {
        assertTrue(WarehouseUtils.sort(null, "capacity", false).isEmpty());
    }

    // ---------------- DUPLICATE ----------------
    @Test
    void testHasDuplicateCodes_true() {
        Warehouse w1 = create("W1", "A", 10, 5);
        Warehouse w2 = create("W1", "B", 20, 10);

        assertTrue(WarehouseUtils.hasDuplicateCodes(List.of(w1, w2)));
    }

    @Test
    void testHasDuplicateCodes_false() {
        Warehouse w1 = create("W1", "A", 10, 5);
        Warehouse w2 = create("W2", "B", 20, 10);

        assertFalse(WarehouseUtils.hasDuplicateCodes(List.of(w1, w2)));
    }

    @Test
    void testHasDuplicateCodes_nullList() {
        assertFalse(WarehouseUtils.hasDuplicateCodes(null));
    }

    // ---------------- SAFE EQUALS ----------------
    @Test
    void testSafeEquals() {
        assertTrue(WarehouseUtils.safeEquals("A", "A"));
        assertFalse(WarehouseUtils.safeEquals("A", "B"));
        assertTrue(WarehouseUtils.safeEquals(null, null));
        assertFalse(WarehouseUtils.safeEquals("A", null));
    }
}