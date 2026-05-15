package com.fulfilment.application.monolith.warehouses.bin;

import com.fulfilment.application.monolith.warehouses.extra.bin.WarehouseOperationalState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseOperationalStateTest {

    @Test
    void canOperate_true() {
        WarehouseOperationalState w = new WarehouseOperationalState();
        w.active = true;
        w.locked = false;

        assertTrue(w.canOperate());
    }

    @Test
    void canOperate_false_locked() {
        WarehouseOperationalState w = new WarehouseOperationalState();
        w.active = true;
        w.locked = true;

        assertFalse(w.canOperate());
    }

    @Test
    void canOperate_false_inactive() {
        WarehouseOperationalState w = new WarehouseOperationalState();
        w.active = false;
        w.locked = false;

        assertFalse(w.canOperate());
    }
}