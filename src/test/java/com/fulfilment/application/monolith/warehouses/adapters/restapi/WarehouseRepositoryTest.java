package com.fulfilment.application.monolith.warehouses.adapters.restapi;

import com.fulfilment.application.monolith.warehouses.adapters.database.WarehouseRepository;
import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class WarehouseRepositoryTest {

    @Inject
    WarehouseRepository repository;

    // ---------------- CREATE SUCCESS ----------------
    @Test
    @Transactional
    void create_success() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "DB-1";
        w.location = "DELHI";
        w.capacity = 100;
        w.stock = 50;
        w.createdAt = LocalDateTime.now();

        repository.create(w);

        Warehouse found = repository.findByBusinessUnitCode("DB-1");
        assertNotNull(found);
        assertEquals("DELHI", found.location);
    }

    // ---------------- CREATE NULL (COVERS CATCH BLOCK) ----------------
    @Test
    void create_null_shouldThrow() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> repository.create(null)
        );

        assertTrue(ex.getMessage().contains("Invalid warehouse data"));
    }

    // ---------------- UPDATE SUCCESS ----------------
    @Test
    @Transactional
    void update_success() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "DB-2";
        w.location = "DELHI";
        w.capacity = 100;
        w.stock = 50;

        repository.create(w);

        Warehouse updated = repository.findByBusinessUnitCode("DB-2");
        updated.stock = 99;

        repository.update(updated);

        Warehouse check = repository.findByBusinessUnitCode("DB-2");
        assertEquals(99, check.stock);
    }

    // ---------------- UPDATE NULL ----------------
    @Test
    void update_null_shouldThrow() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> repository.update(null)
        );

        assertTrue(ex.getMessage().contains("Invalid warehouse data"));
    }

    // ---------------- UPDATE NOT FOUND ----------------
    @Test
    @Transactional
    void update_notFound_shouldThrow() {

        Warehouse w = new Warehouse();
        w.businessUnitCode = "NOT-FOUND";
        w.location = "DELHI";

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> repository.update(w)
        );

        assertTrue(ex.getMessage().contains("Warehouse not found"));
    }

    // ---------------- FIND NULL ----------------
    @Test
    void find_null_shouldThrow() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> repository.findByBusinessUnitCode(null)
        );

        assertTrue(ex.getMessage().contains("Invalid business unit code"));
    }

    // ---------------- FIND NOT EXISTS ----------------
    @Test
    void find_notExists_shouldReturnNull() {
        Warehouse w = repository.findByBusinessUnitCode("NO-SUCH");
        assertNull(w);
    }

    // ---------------- REMOVE FLOW ----------------
    @Test
    @Transactional
    void remove_success() {

        Warehouse w = new Warehouse();
        w.businessUnitCode = "DB-3";
        w.location = "DELHI";

        repository.create(w);

        repository.remove(w);

        Warehouse found = repository.findByBusinessUnitCode("DB-3");

        // still exists but archivedAt set
        assertNotNull(found);
    }

    // ---------------- REMOVE NULL ----------------
    @Test
    void remove_null_shouldThrow() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> repository.remove(null)
        );

        assertTrue(ex.getMessage().contains("Invalid warehouse data"));
    }

    // ---------------- GET ALL ----------------
    @Test
    @Transactional
    void getAll_success() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "DB-4";
        w.location = "DELHI";

        repository.create(w);

        assertFalse(repository.getAll().isEmpty());
    }
}