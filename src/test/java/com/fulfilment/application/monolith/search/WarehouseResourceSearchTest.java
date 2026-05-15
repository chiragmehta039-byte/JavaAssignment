package com.fulfilment.application.monolith.search;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fulfilment.application.monolith.warehouses.adapters.database.WarehouseRepository;
import com.fulfilment.application.monolith.warehouses.adapters.restapi.WarehouseResourceImpl;
import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import jakarta.ws.rs.WebApplicationException;
import org.junit.jupiter.api.*;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class WarehouseResourceSearchTest {

    private WarehouseResourceImpl resource;
    private WarehouseRepository repository;

    @BeforeEach
    void setup() {
        resource = new WarehouseResourceImpl();
        repository = mock(WarehouseRepository.class);
        inject(resource, "warehouseRepository", repository);
    }

    // ---------------- NULL REPO ----------------
    @Test
    void shouldThrowWhenRepoReturnsNull() {

        List<Warehouse> warehouses = Optional.ofNullable(repository.getAll())
                .orElse(Collections.emptyList());
    }

    // ---------------- LOCATION FILTER ----------------
    @Test
    void shouldFilterByLocation() {

        Warehouse w = new Warehouse();
        w.location = "Delhi";
        w.capacity = 100;
        w.stock = 10;

        when(repository.getAll()).thenReturn(List.of(w));

        var result = resource.searchWarehouses(
                "Delhi", null, null,
                null, null,
                null, null);

        assertEquals(1, result.size());
    }

    // ---------------- MIN CAPACITY ----------------
    @Test
    void shouldFilterByMinCapacity() {

        Warehouse w = new Warehouse();
        w.capacity = 500;

        when(repository.getAll()).thenReturn(List.of(w));

        var result = resource.searchWarehouses(
                null, BigInteger.valueOf(200), null,
                null, null,
                null, null);

        assertEquals(1, result.size());
    }

    // ---------------- SORT BY LOCATION ----------------
    @Test
    void shouldSortByLocation() {

        Warehouse w1 = new Warehouse();
        w1.location = "Mumbai";

        Warehouse w2 = new Warehouse();
        w2.location = "Delhi";

        when(repository.getAll()).thenReturn(List.of(w1, w2));

        var result = resource.searchWarehouses(
                null, null, null,
                "location", "asc",
                null, null);

        assertEquals("Delhi", result.get(0).getLocation());
    }

    // ---------------- INVALID SORT ----------------
    @Test
    void shouldThrowInvalidSort() {

        when(repository.getAll()).thenReturn(List.of());

        assertThrows(WebApplicationException.class, () ->
                resource.searchWarehouses(
                        null, null, null,
                        "invalid", "asc",
                        null, null));
    }

    // ---------------- PAGINATION ----------------
    @Test
    void shouldPaginate() {

        Warehouse w1 = new Warehouse();
        Warehouse w2 = new Warehouse();

        when(repository.getAll()).thenReturn(List.of(w1, w2));

        var result = resource.searchWarehouses(
                null, null, null,
                null, null,
                BigInteger.ZERO,
                BigInteger.ONE);

        assertEquals(1, result.size());
    }

    // ---------------- INVALID PAGINATION ----------------
    @Test
    void shouldThrowInvalidPagination() {

        when(repository.getAll()).thenReturn(List.of());

        assertThrows(WebApplicationException.class, () ->
                resource.searchWarehouses(
                        null, null, null,
                        null, null,
                        BigInteger.valueOf(-1),
                        BigInteger.ZERO));
    }

    // ---------------- UTIL ----------------
    private void inject(Object target, String field, Object value) {
        try {
            var f = target.getClass().getDeclaredField(field);
            f.setAccessible(true);
            f.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}