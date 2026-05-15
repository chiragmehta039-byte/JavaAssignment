package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseFreezeEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseFreezeEventTest {

    @Test
    void locked() {
        assertEquals("LOCKED", DbWarehouseFreezeEvent.create(true, "ANY").level);
    }

    @Test
    void temp() {
        assertEquals("TEMP", DbWarehouseFreezeEvent.create(false, "MAINTENANCE").level);
    }

    @Test
    void active() {
        assertEquals("ACTIVE", DbWarehouseFreezeEvent.create(false, "OK").level);
    }
}