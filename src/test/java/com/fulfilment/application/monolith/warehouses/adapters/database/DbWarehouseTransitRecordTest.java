package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseTransitRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseTransitRecordTest {

    @Test
    void arrived() {
        assertEquals("ARRIVED", DbWarehouseTransitRecord.create("S1", "IN").state);
    }

    @Test
    void dispatched() {
        assertEquals("DISPATCHED", DbWarehouseTransitRecord.create("S1", "OUT").state);
    }

    @Test
    void hold() {
        assertEquals("HOLD", DbWarehouseTransitRecord.create("S1", "OTHER").state);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehouseTransitRecord.create("S1", null).state);
    }
}