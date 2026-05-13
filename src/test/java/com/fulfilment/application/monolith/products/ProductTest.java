package com.fulfilment.application.monolith.products;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testDefaultConstructor() {
        Product product = new Product();

        assertNotNull(product);
        assertNull(product.id);
        assertNull(product.name);
        assertNull(product.description);
        assertNull(product.price);
        assertEquals(0, product.stock);
    }

    @Test
    public void testNameConstructor() {
        Product product = new Product("Phone");

        assertEquals("Phone", product.name);
        assertNull(product.id);
    }

    @Test
    public void testFieldAssignments() {
        Product product = new Product();

        product.id = 1L;
        product.name = "Laptop";
        product.description = "Gaming laptop";
        product.price = new BigDecimal("50000.50");
        product.stock = 10;

        assertEquals(1L, product.id);
        assertEquals("Laptop", product.name);
        assertEquals("Gaming laptop", product.description);
        assertEquals(new BigDecimal("50000.50"), product.price);
        assertEquals(10, product.stock);
    }
}