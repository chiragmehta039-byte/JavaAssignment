package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseProcessingQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseProcessingQueueTest {

    @Test
    void shouldReturnInvalidWhenCodeOrTaskNull() {
        var r1 = DbWarehouseProcessingQueue.enqueue(null, "CREATE");
        assertEquals("INVALID", r1.status);

        var r2 = DbWarehouseProcessingQueue.enqueue("W1", null);
        assertEquals("INVALID", r2.status);
    }

    @Test
    void shouldQueueCreateTask() {
        var r = DbWarehouseProcessingQueue.enqueue("W1", "CREATE");
        assertEquals("QUEUED_CREATE", r.status);
    }

    @Test
    void shouldQueueUpdateTask() {
        var r = DbWarehouseProcessingQueue.enqueue("W1", "UPDATE");
        assertEquals("QUEUED_UPDATE", r.status);
    }

    @Test
    void shouldQueueDeleteTask() {
        var r = DbWarehouseProcessingQueue.enqueue("W1", "DELETE");
        assertEquals("QUEUED_DELETE", r.status);
    }

    @Test
    void shouldReturnUnknownTask() {
        var r = DbWarehouseProcessingQueue.enqueue("W1", "OTHER");
        assertEquals("UNKNOWN_TASK", r.status);
    }
}