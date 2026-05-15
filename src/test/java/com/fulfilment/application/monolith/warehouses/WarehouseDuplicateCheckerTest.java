package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.extra.WarehouseDuplicateChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WarehouseDuplicateCheckerTest {

    @Test
    void testDuplicateTrue() {
        Warehouse w1 = new Warehouse();
        w1.businessUnitCode = "A";

        Warehouse w2 = new Warehouse();
        w2.businessUnitCode = "A";

        assertTrue(WarehouseDuplicateChecker.hasDuplicates(java.util.Arrays.asList(w1, w2)));
    }

    @Test
    void testDuplicateFalse() {
        Warehouse w1 = new Warehouse();
        w1.businessUnitCode = "A";

        Warehouse w2 = new Warehouse();
        w2.businessUnitCode = "B";

        assertFalse(WarehouseDuplicateChecker.hasDuplicates(java.util.Arrays.asList(w1, w2)));
    }

    @Test
    void testNullList() {
        assertFalse(WarehouseDuplicateChecker.hasDuplicates(null));
    }
}