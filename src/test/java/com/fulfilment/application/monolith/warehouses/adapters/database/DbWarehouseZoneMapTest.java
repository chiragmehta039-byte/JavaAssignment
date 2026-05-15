package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseZoneMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseZoneMapTest {

    @Test
    void danger() {
        assertEquals("DANGER", DbWarehouseZoneMap.create("Z1", 90).level);
    }

    @Test
    void warning() {
        assertEquals("WARNING", DbWarehouseZoneMap.create("Z1", 60).level);
    }

    @Test
    void safe() {
        assertEquals("SAFE", DbWarehouseZoneMap.create("Z1", 10).level);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehouseZoneMap.create("Z1", null).level);
    }
}