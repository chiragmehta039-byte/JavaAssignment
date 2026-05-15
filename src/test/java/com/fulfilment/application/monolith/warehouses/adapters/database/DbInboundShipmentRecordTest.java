package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbInboundShipmentRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbInboundShipmentRecordTest {

    @Test
    void normalShipment() {
        assertEquals("NORMAL", DbInboundShipmentRecord.create("S1", 50).status);
    }

    @Test
    void heavyShipment() {
        assertEquals("HEAVY", DbInboundShipmentRecord.create("S2", 200).status);
    }

    @Test
    void invalidShipment() {
        assertEquals("INVALID", DbInboundShipmentRecord.create("S3", 0).status);
    }
}