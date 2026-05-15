package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class WarehouseRepositoryExtraTest {

    @Inject
    WarehouseRepository repository;

    // ---------------------------
    // TEST: create + getAll
    // ---------------------------
    @Test
    @Transactional
    void testCreateAndGetAll() {

        Warehouse w = new Warehouse();
        w.businessUnitCode = "EXTRA-001";
        w.location = "DELHI";
        w.capacity = 50;
        w.stock = 10;
        w.createdAt = LocalDateTime.now();

        repository.create(w);

        List<Warehouse> all = repository.getAll();

        assertTrue(all.stream()
                .anyMatch(x -> "EXTRA-001".equals(x.businessUnitCode)));
    }

    // ---------------------------
    // TEST: create null (NPE branch)
    // ---------------------------
    @Test
    void testCreateNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            repository.create(null);
        });
    }

    // ---------------------------
    // TEST: update not found
    // ---------------------------
    @Test
    @Transactional
    void testUpdateNotFound() {

        Warehouse w = new Warehouse();
        w.businessUnitCode = "NOT-EXIST";

        assertThrows(IllegalArgumentException.class, () -> {
            repository.update(w);
        });
    }

    // ---------------------------
    // TEST: remove not found
    // ---------------------------
    @Test
    @Transactional
    void testRemoveNotFound() {

        Warehouse w = new Warehouse();
        w.businessUnitCode = "UNKNOWN";

        // should NOT crash
        repository.remove(w);
    }

    // ---------------------------
    // TEST: remove success
    // ---------------------------
    @Test
    @Transactional
    void testRemoveSuccess() {

        Warehouse w = new Warehouse();
        w.businessUnitCode = "EXTRA-DELETE";
        w.location = "MUMBAI";
        w.capacity = 40;
        w.stock = 5;
        w.createdAt = LocalDateTime.now();

        repository.create(w);

        repository.remove(w);

        Warehouse found = repository.findByBusinessUnitCode("EXTRA-DELETE");

        assertNotNull(found);
        assertNotNull(found.archivedAt);
    }

    // ---------------------------
    // TEST: find null input
    // ---------------------------
    @Test
    void testFindNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            repository.findByBusinessUnitCode(null);
        });
    }

    // ---------------------------
    // TEST: find existing
    // ---------------------------
    @Test
    @Transactional
    void testFindExisting() {

        Warehouse w = new Warehouse();
        w.businessUnitCode = "EXTRA-FIND";
        w.location = "PUNE";
        w.capacity = 70;
        w.stock = 20;
        w.createdAt = LocalDateTime.now();

        repository.create(w);

        Warehouse found = repository.findByBusinessUnitCode("EXTRA-FIND");

        assertNotNull(found);
        assertEquals("PUNE", found.location);
    }
}