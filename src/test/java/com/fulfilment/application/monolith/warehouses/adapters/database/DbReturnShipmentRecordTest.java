package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbReturnShipmentRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbReturnShipmentRecordTest {

    @Test
    void accepted() {
        assertEquals("ACCEPTED", DbReturnShipmentRecord.create("R1", 10, "OK").reason);
    }

    @Test
    void blocked() {
        assertEquals("BLOCKED", DbReturnShipmentRecord.create("R2", 10, "DAMAGED").reason);
    }

    @Test
    void invalid() {
        assertEquals("INVALID", DbReturnShipmentRecord.create("R3", 0, "OK").reason);
    }
}