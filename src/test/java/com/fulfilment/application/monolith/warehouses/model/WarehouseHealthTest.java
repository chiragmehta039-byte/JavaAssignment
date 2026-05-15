package com.fulfilment.application.monolith.warehouses.model;

import com.fulfilment.application.monolith.warehouses.domain.models.WarehouseHealth;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseHealthTest {

    @Test
    void shouldReturnTrueWhenPowerFailure() {
        WarehouseHealth h = new WarehouseHealth();
        h.powerFailure = true;
        assertTrue(h.isCritical());
    }

    @Test
    void shouldReturnTrueWhenFireAlert() {
        WarehouseHealth h = new WarehouseHealth();
        h.fireAlert = true;
        assertTrue(h.isCritical());
    }

    @Test
    void shouldReturnFalseWhenNoCriticalIssue() {
        WarehouseHealth h = new WarehouseHealth();
        assertFalse(h.isCritical());
    }

    @Test
    void shouldDetectHighTemperature() {
        WarehouseHealth h = new WarehouseHealth();
        h.temperature = 50;
        assertTrue(h.isEnvironmentUnsafe());
    }

    @Test
    void shouldDetectHighHumidity() {
        WarehouseHealth h = new WarehouseHealth();
        h.humidity = 90;
        assertTrue(h.isEnvironmentUnsafe());
    }

    @Test
    void shouldBeSafeWhenNullValues() {
        WarehouseHealth h = new WarehouseHealth();
        assertFalse(h.isEnvironmentUnsafe());
    }
}