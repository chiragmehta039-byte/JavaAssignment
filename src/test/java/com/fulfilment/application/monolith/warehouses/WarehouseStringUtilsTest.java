package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.extra.WarehouseStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseStringUtilsTest {

    @Test
    void testEqualsIgnoreCase() {
        assertTrue(WarehouseStringUtils.equalsIgnoreCase("abc", "ABC"));
        assertFalse(WarehouseStringUtils.equalsIgnoreCase("a", "b"));
        assertTrue(WarehouseStringUtils.equalsIgnoreCase(null, null));
    }

    @Test
    void testSafeUpper() {
        assertEquals("ABC", WarehouseStringUtils.safeUpper("abc"));
        assertEquals("", WarehouseStringUtils.safeUpper(null));
    }
}