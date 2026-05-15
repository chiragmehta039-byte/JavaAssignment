package com.fulfilment.application.monolith.warehouses.model;

import com.fulfilment.application.monolith.warehouses.domain.models.WarehouseRiskProfile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseRiskProfileTest {

    @Test
    void shouldBeHighRiskWhenTheft() {
        WarehouseRiskProfile r = new WarehouseRiskProfile();
        r.theftRisk = true;

        assertTrue(r.isHighRisk());
    }

    @Test
    void shouldBeHighRiskWhenDelay() {
        WarehouseRiskProfile r = new WarehouseRiskProfile();
        r.delayRisk = true;

        assertTrue(r.isHighRisk());
    }

    @Test
    void shouldBeSafeWhenNoRisk() {
        WarehouseRiskProfile r = new WarehouseRiskProfile();

        assertTrue(r.isSafe());
    }

    @Test
    void shouldReturnFalseForHighRiskCheck() {
        WarehouseRiskProfile r = new WarehouseRiskProfile();

        assertFalse(r.isHighRisk());
    }
}