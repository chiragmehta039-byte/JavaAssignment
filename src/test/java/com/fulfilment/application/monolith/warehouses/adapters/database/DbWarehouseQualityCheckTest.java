package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseQualityCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseQualityCheckTest {

    @Test
    void pass() {
        assertEquals("PASS", DbWarehouseQualityCheck.create(true, 2).grade);
    }

    @Test
    void fail() {
        assertEquals("FAIL", DbWarehouseQualityCheck.create(false, 20).grade);
    }

    @Test
    void review() {
        assertEquals("REVIEW", DbWarehouseQualityCheck.create(false, 5).grade);
    }
}