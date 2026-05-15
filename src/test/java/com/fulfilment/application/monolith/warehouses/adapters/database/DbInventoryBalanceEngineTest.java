package com.fulfilment.application.monolith.warehouses.adapters.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbInventoryBalanceEngineTest {

    @Test
    void shouldReturnMinusOneWhenCodeNull() {
        var r = DbInventoryBalanceEngine.calculate(null, 10, 5);
        assertEquals(-1, r.balance);
    }

    @Test
    void shouldReturnZeroWhenAnyValueNull() {
        var r1 = DbInventoryBalanceEngine.calculate("I1", null, 5);
        assertEquals(0, r1.balance);

        var r2 = DbInventoryBalanceEngine.calculate("I1", 10, null);
        assertEquals(0, r2.balance);
    }

    @Test
    void shouldCalculateBalanceCorrectly() {
        var r = DbInventoryBalanceEngine.calculate("I1", 20, 5);
        assertEquals(15, r.balance);
    }
}