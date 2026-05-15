package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseAuditTrailExtended;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseAuditTrailExtendedTest {

    @Test
    void delete() {
        assertEquals("HIGH", DbWarehouseAuditTrailExtended.create("DELETE","u1").severity);
    }

    @Test
    void update() {
        assertEquals("MEDIUM", DbWarehouseAuditTrailExtended.create("UPDATE","u1").severity);
    }

    @Test
    void create() {
        assertEquals("LOW", DbWarehouseAuditTrailExtended.create("CREATE","u1").severity);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehouseAuditTrailExtended.create(null,"u1").severity);
    }
}