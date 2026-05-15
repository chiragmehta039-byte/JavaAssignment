package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouseLoadBalancer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbWarehouseLoadBalancerTest {

    @Test
    void allow() {
        assertEquals("ALLOW", DbWarehouseLoadBalancer.create(30).decision);
    }

    @Test
    void delay() {
        assertEquals("DELAY", DbWarehouseLoadBalancer.create(70).decision);
    }

    @Test
    void reject() {
        assertEquals("REJECT", DbWarehouseLoadBalancer.create(95).decision);
    }

    @Test
    void ignore() {
        assertEquals("IGNORE", DbWarehouseLoadBalancer.create(null).decision);
    }
}