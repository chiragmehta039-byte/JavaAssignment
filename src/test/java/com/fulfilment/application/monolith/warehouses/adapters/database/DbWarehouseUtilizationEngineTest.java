package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseUtilizationEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseUtilizationEngineTest {

    @Test
    void invalid() {
        assertEquals("INVALID",
                DbWarehouseUtilizationEngine.calculate(null, 10, 100).utilizationStatus);
    }

    @Test
    void error_zero_total() {
        assertEquals("ERROR",
                DbWarehouseUtilizationEngine.calculate("W1", 10, 0).utilizationStatus);
    }

    @Test
    void critical() {
        assertEquals("CRITICAL",
                DbWarehouseUtilizationEngine.calculate("W1", 95, 100).utilizationStatus);
    }

    @Test
    void warning() {
        assertEquals("WARNING",
                DbWarehouseUtilizationEngine.calculate("W1", 80, 100).utilizationStatus);
    }

    @Test
    void normal() {
        assertEquals("NORMAL",
                DbWarehouseUtilizationEngine.calculate("W1", 40, 100).utilizationStatus);
    }
}