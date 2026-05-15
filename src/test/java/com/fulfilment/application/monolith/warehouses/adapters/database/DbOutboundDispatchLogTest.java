package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbOutboundDispatchLog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbOutboundDispatchLogTest {

    @Test
    void highPriority() {
        assertEquals("HIGH", DbOutboundDispatchLog.create("O1", "HIGH").priority);
    }

    @Test
    void mediumPriority() {
        assertEquals("MEDIUM", DbOutboundDispatchLog.create("O2", "NORMAL").priority);
    }

    @Test
    void defaultPriority() {
        assertEquals("LOW", DbOutboundDispatchLog.create("O3", null).priority);
    }
}