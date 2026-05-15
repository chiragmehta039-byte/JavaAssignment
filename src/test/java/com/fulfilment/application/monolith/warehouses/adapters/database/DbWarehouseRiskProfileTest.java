package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseRiskProfile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseRiskProfileTest {

    @Test
    void high() {
        assertEquals("HIGH", DbWarehouseRiskProfile.create(true, false).status);
    }

    @Test
    void low() {
        assertEquals("LOW", DbWarehouseRiskProfile.create(false, false).status);
    }
}