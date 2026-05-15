package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehousePriorityQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehousePriorityQueueTest {

    @Test
    void expedite() {
        assertEquals("EXPEDITE", DbWarehousePriorityQueue.create(90).decision);
    }

    @Test
    void normal() {
        assertEquals("NORMAL", DbWarehousePriorityQueue.create(50).decision);
    }

    @Test
    void defer() {
        assertEquals("DEFER", DbWarehousePriorityQueue.create(10).decision);
    }

    @Test
    void ignore() {
        assertEquals("IGNORE", DbWarehousePriorityQueue.create(null).decision);
    }
}