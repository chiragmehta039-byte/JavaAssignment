package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseOrderQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseOrderQueueTest {

    @Test
    void invalid_order() {
        var q = DbWarehouseOrderQueue.create(null, 10);
        assertEquals("INVALID", q.queueStatus);
    }

    @Test
    void default_priority() {
        var q = DbWarehouseOrderQueue.create("O1", null);
        assertEquals("DEFAULT", q.queueStatus);
    }

    @Test
    void express_priority() {
        var q = DbWarehouseOrderQueue.create("O1", 90);
        assertEquals("EXPRESS", q.queueStatus);
    }

    @Test
    void normal_priority() {
        var q = DbWarehouseOrderQueue.create("O1", 60);
        assertEquals("NORMAL", q.queueStatus);
    }

    @Test
    void slow_priority() {
        var q = DbWarehouseOrderQueue.create("O1", 20);
        assertEquals("SLOW", q.queueStatus);
    }
}