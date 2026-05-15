package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbInventoryAdjustmentLog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbInventoryAdjustmentLogTest {

    @Test
    void increase() {
        assertEquals("INCREASE", DbInventoryAdjustmentLog.create("SKU1", 10).type);
    }

    @Test
    void decrease() {
        assertEquals("DECREASE", DbInventoryAdjustmentLog.create("SKU1", -5).type);
    }

    @Test
    void ignore() {
        assertEquals("IGNORE", DbInventoryAdjustmentLog.create("SKU1", null).type);
    }
}