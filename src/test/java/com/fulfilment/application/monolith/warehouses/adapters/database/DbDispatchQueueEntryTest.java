package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbDispatchQueueEntry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbDispatchQueueEntryTest {

    @Test
    void high() {
        assertEquals("HIGH", DbDispatchQueueEntry.create("O1", 10).state);
    }

    @Test
    void medium() {
        assertEquals("MEDIUM", DbDispatchQueueEntry.create("O2", 3).state);
    }

    @Test
    void low() {
        assertEquals("LOW", DbDispatchQueueEntry.create("O3", null).state);
    }
}