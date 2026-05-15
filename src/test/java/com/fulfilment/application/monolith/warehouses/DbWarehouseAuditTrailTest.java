package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseAuditTrail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DbWarehouseAuditTrailTest {

    @Test
    void invalid() {
        assertEquals("INVALID",
                DbWarehouseAuditTrail.log(null, null, true).result);
    }

    @Test
    void create() {
        assertEquals("CREATED",
                DbWarehouseAuditTrail.log("W1", "CREATE", true).result);
    }

    @Test
    void update() {
        assertEquals("UPDATED",
                DbWarehouseAuditTrail.log("W1", "UPDATE", true).result);
    }

    @Test
    void delete() {
        assertEquals("DELETED",
                DbWarehouseAuditTrail.log("W1", "DELETE", true).result);
    }

    @Test
    void failed() {
        assertEquals("FAILED",
                DbWarehouseAuditTrail.log("W1", "CREATE", false).result);
    }
}