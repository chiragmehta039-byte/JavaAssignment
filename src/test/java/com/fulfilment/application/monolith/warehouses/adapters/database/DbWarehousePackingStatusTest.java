package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehousePackingStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehousePackingStatusTest {

    @Test
    void normal() {
        assertEquals("NORMAL", DbWarehousePackingStatus.create("O1", 10).status);
    }

    @Test
    void heavy() {
        assertEquals("HEAVY", DbWarehousePackingStatus.create("O2", 30).status);
    }

    @Test
    void empty() {
        assertEquals("EMPTY", DbWarehousePackingStatus.create("O3", 0).status);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehousePackingStatus.create("O4", null).status);
    }
}