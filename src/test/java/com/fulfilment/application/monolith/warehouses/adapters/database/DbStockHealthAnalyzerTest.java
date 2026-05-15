package com.fulfilment.application.monolith.warehouses.adapters.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbStockHealthAnalyzerTest {

    @Test
    void shouldReturnInvalidWhenCodeNull() {
        var r = DbStockHealthAnalyzer.analyze(null, 10);
        assertEquals("INVALID", r.healthStatus);
    }

    @Test
    void shouldReturnUnknownWhenStockNull() {
        var r = DbStockHealthAnalyzer.analyze("I1", null);
        assertEquals("UNKNOWN", r.healthStatus);
    }

    @Test
    void shouldReturnEmptyWhenZeroStock() {
        var r = DbStockHealthAnalyzer.analyze("I1", 0);
        assertEquals("EMPTY", r.healthStatus);
    }

    @Test
    void shouldReturnLowWhenStockLessThan20() {
        var r = DbStockHealthAnalyzer.analyze("I1", 10);
        assertEquals("LOW", r.healthStatus);
    }

    @Test
    void shouldReturnOkWhenStockHigh() {
        var r = DbStockHealthAnalyzer.analyze("I1", 50);
        assertEquals("OK", r.healthStatus);
    }
}