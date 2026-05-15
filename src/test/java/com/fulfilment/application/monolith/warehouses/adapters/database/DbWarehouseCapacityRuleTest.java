package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseCapacityRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseCapacityRuleTest {

    @Test
    void valid() {
        assertEquals("VALID", DbWarehouseCapacityRule.create(10, 100).status);
    }

    @Test
    void broken() {
        assertEquals("BROKEN", DbWarehouseCapacityRule.create(100, 10).status);
    }

    @Test
    void invalid() {
        assertEquals("INVALID", DbWarehouseCapacityRule.create(null, 10).status);
    }
}