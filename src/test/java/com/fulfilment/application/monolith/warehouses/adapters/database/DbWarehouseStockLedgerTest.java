package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseStockLedger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseStockLedgerTest {

    @Test
    void shouldReturnMinusOneWhenCodeIsNull() {
        var result = DbWarehouseStockLedger.calculate(null, 10, 5);

        assertEquals(-1, result.currentStock);
    }

    @Test
    void shouldReturnZeroWhenInboundOrOutboundIsNull() {
        var result1 = DbWarehouseStockLedger.calculate("W1", null, 5);
        assertEquals(0, result1.currentStock);

        var result2 = DbWarehouseStockLedger.calculate("W1", 10, null);
        assertEquals(0, result2.currentStock);
    }

    @Test
    void shouldCalculateCorrectStock() {
        var result = DbWarehouseStockLedger.calculate("W1", 20, 5);

        assertEquals(15, result.currentStock);
    }

    @Test
    void shouldHandleZeroValues() {
        var result = DbWarehouseStockLedger.calculate("W1", 0, 0);

        assertEquals(0, result.currentStock);
    }

    @Test
    void shouldHandleNegativeValues() {
        var result = DbWarehouseStockLedger.calculate("W1", -10, -5);

        assertEquals(-5, result.currentStock);
    }
}