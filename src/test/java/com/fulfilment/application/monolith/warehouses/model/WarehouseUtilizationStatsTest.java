package com.fulfilment.application.monolith.warehouses.model;

import com.fulfilment.application.monolith.warehouses.domain.models.WarehouseUtilizationStats;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseUtilizationStatsTest {

    @Test
    void shouldBeBusyWhenInboundHigh() {
        WarehouseUtilizationStats s = new WarehouseUtilizationStats();
        s.inboundShipments = 150;

        assertTrue(s.isBusy());
    }

    @Test
    void shouldBeBusyWhenOutboundHigh() {
        WarehouseUtilizationStats s = new WarehouseUtilizationStats();
        s.outboundShipments = 150;

        assertTrue(s.isBusy());
    }

    @Test
    void shouldBeIdle() {
        WarehouseUtilizationStats s = new WarehouseUtilizationStats();
        s.inboundShipments = 0;
        s.outboundShipments = 0;

        assertTrue(s.isIdle());
    }

    @Test
    void shouldNotBeIdleWhenNull() {
        WarehouseUtilizationStats s = new WarehouseUtilizationStats();

        assertFalse(s.isIdle());
    }
}