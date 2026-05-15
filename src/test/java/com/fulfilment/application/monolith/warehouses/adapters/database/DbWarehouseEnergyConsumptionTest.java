package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseEnergyConsumption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseEnergyConsumptionTest {

    @Test
    void low() {
        assertEquals("LOW", DbWarehouseEnergyConsumption.create(100).grade);
    }

    @Test
    void medium() {
        assertEquals("MEDIUM", DbWarehouseEnergyConsumption.create(600).grade);
    }

    @Test
    void high() {
        assertEquals("HIGH", DbWarehouseEnergyConsumption.create(2000).grade);
    }

    @Test
    void unknown() {
        assertEquals("UNKNOWN", DbWarehouseEnergyConsumption.create(null).grade);
    }
}