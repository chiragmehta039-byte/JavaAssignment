package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseAlertEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseAlertEngineTest {

    @Test
    void shouldReturnInvalidWhenCodeNull() {
        var r = DbWarehouseAlertEngine.evaluate(null, true, false);
        assertEquals("INVALID", r.alertLevel);
    }

    @Test
    void shouldReturnOverstock() {
        var r = DbWarehouseAlertEngine.evaluate("W1", true, false);
        assertEquals("OVERSTOCK", r.alertLevel);
    }

    @Test
    void shouldReturnUnderstock() {
        var r = DbWarehouseAlertEngine.evaluate("W1", false, true);
        assertEquals("UNDERSTOCK", r.alertLevel);
    }

    @Test
    void shouldReturnOk() {
        var r = DbWarehouseAlertEngine.evaluate("W1", false, false);
        assertEquals("OK", r.alertLevel);
    }
}