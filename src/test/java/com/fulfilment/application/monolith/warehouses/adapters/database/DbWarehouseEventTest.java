package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseEventTest {

    @Test
    void shouldCreateLoadEvent() {
        DbWarehouseEvent e = DbWarehouseEvent.create("B1", "LOAD", 10);

        assertEquals("LOADED", e.status);
    }

    @Test
    void shouldCreateUnloadEvent() {
        DbWarehouseEvent e = DbWarehouseEvent.create("B1", "UNLOAD", 10);

        assertEquals("UNLOADED", e.status);
    }

    @Test
    void shouldCreateTransferEvent() {
        DbWarehouseEvent e = DbWarehouseEvent.create("B1", "MOVE", 10);

        assertEquals("TRANSFERRED", e.status);
    }

    @Test
    void shouldMarkInvalidWhenQtyZero() {
        DbWarehouseEvent e = DbWarehouseEvent.create("B1", "LOAD", 0);

        assertEquals("INVALID", e.status);
    }

    @Test
    void shouldMarkInvalidWhenQtyNull() {
        DbWarehouseEvent e = DbWarehouseEvent.create("B1", "LOAD", null);

        assertEquals("INVALID", e.status);
    }
}