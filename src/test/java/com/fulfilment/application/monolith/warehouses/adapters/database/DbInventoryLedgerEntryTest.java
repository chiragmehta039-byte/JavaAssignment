package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbInventoryLedgerEntry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbInventoryLedgerEntryTest {

    @Test
    void shouldSetChange() {
        DbInventoryLedgerEntry e = DbInventoryLedgerEntry.create("SKU1", 10);

        assertEquals(10, e.change);
    }

    @Test
    void shouldDefaultChangeToZero() {
        DbInventoryLedgerEntry e = DbInventoryLedgerEntry.create("SKU1", null);

        assertEquals(0, e.change);
    }
}