package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.service.WarehouseSearchService;
import jakarta.ws.rs.WebApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseSearchServiceTest {

    private WarehouseSearchService service;

    @BeforeEach
    void setup() {
        service = new WarehouseSearchService();
    }

    private Warehouse create(String code, String loc, Integer cap, int stock) {
        Warehouse w = new Warehouse();
        w.businessUnitCode = code;
        w.location = loc;
        w.capacity = cap;
        w.stock = stock;
        return w;
    }

    // ---------------- NULL LIST ----------------
    @Test
    void testNullWarehouseList() {
        assertThrows(WebApplicationException.class, () ->
                service.search(null, null, null, null, null, null, null, null));
    }

    // ---------------- FILTER ----------------
    @Test
    void testFilterByLocation() {
        Warehouse w1 = create("W1", "DELHI", 10, 5);
        Warehouse w2 = create("W2", "MUMBAI", 20, 10);

        List<Warehouse> result = service.search(
                List.of(w1, w2),
                "DELHI",
                null,
                null,
                null,
                null,
                null,
                null
        );

        assertEquals(1, result.size());
    }

    @Test
    void testFilterByCapacityRange() {
        Warehouse w1 = create("W1", "A", 10, 5);
        Warehouse w2 = create("W2", "A", 50, 10);

        List<Warehouse> result = service.search(
                List.of(w1, w2),
                null,
                BigInteger.valueOf(20),
                BigInteger.valueOf(60),
                null,
                null,
                null,
                null
        );

        assertEquals(1, result.size());
    }

    @Test
    void testFilterArchivedExcluded() {
        Warehouse active = create("W1", "A", 10, 5);
        Warehouse archived = create("W2", "A", 20, 10);
        archived.archivedAt = LocalDateTime.now();

        List<Warehouse> result = service.search(
                List.of(active, archived),
                null, null, null, null, null, null, null
        );

        assertEquals(1, result.size());
    }

    // ---------------- SORT ----------------
    @Test
    void testSortByCapacityAsc() {
        Warehouse w1 = create("W1", "A", 50, 5);
        Warehouse w2 = create("W2", "A", 10, 10);

        List<Warehouse> result = service.search(
                List.of(w1, w2),
                null, null, null,
                "capacity",
                "asc",
                null, null
        );

        assertTrue(result.get(0).capacity <= result.get(1).capacity);
    }

    @Test
    void testSortByCapacityDesc() {
        Warehouse w1 = create("W1", "A", 10, 5);
        Warehouse w2 = create("W2", "A", 50, 10);

        List<Warehouse> result = service.search(
                List.of(w1, w2),
                null, null, null,
                "capacity",
                "desc",
                null, null
        );

        assertTrue(result.get(0).capacity >= result.get(1).capacity);
    }

    @Test
    void testSortInvalidField() {
        Warehouse w = create("W1", "A", 10, 5);

        assertThrows(WebApplicationException.class, () ->
                service.search(
                        List.of(w),
                        null, null, null,
                        "invalid",
                        null,
                        null, null
                )
        );
    }

    // ---------------- PAGINATION ----------------
    @Test
    void testPaginationLimit() {
        Warehouse w1 = create("W1", "A", 10, 5);
        Warehouse w2 = create("W2", "A", 20, 10);

        List<Warehouse> result = service.search(
                List.of(w1, w2),
                null, null, null,
                null, null,
                BigInteger.ZERO,
                BigInteger.ONE
        );

        assertEquals(1, result.size());
    }

    @Test
    void testPaginationNegativePage() {
        Warehouse w = create("W1", "A", 10, 5);

        assertThrows(WebApplicationException.class, () ->
                service.search(
                        List.of(w),
                        null, null, null,
                        null, null,
                        BigInteger.valueOf(-1),
                        BigInteger.ONE
                )
        );
    }

    @Test
    void testPaginationInvalidSize() {
        Warehouse w = create("W1", "A", 10, 5);

        assertThrows(WebApplicationException.class, () ->
                service.search(
                        List.of(w),
                        null, null, null,
                        null, null,
                        BigInteger.ZERO,
                        BigInteger.ZERO
                )
        );
    }

    // ---------------- EDGE CASE ----------------
    @Test
    void testNullWarehouseInsideListIgnored() {
        Warehouse w1 = create("W1", "DELHI", 10, 5);
        Warehouse w2 = create("W2", "MUMBAI", 20, 10);

        List<Warehouse> list = java.util.Arrays.asList(w1, null, w2); // ✅ FIX

        List<Warehouse> result = service.search(
                list,
                null, null, null,
                null, null,
                null, null
        );

        assertEquals(2, result.size()); // null should be ignored
    }
}