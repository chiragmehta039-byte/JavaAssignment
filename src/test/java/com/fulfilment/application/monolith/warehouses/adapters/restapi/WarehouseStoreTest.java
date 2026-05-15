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
class WarehouseStoreTest {

    @Inject
    WarehouseRepository repository;

    // ---------------- CREATE ----------------
    @Test
    @Transactional
    void create_success() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "S-1";
        w.location = "DELHI";
        w.capacity = 100;
        w.stock = 50;
        w.createdAt = LocalDateTime.now();

        repository.create(w);

        Warehouse found = repository.findByBusinessUnitCode("S-1");
        assertNotNull(found);
        assertEquals("DELHI", found.location);
    }

    // ---------------- CREATE NULL ----------------
    @Test
    void create_null_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.create(null));
    }

    // ---------------- UPDATE SUCCESS ----------------
    @Test
    @Transactional
    void update_success() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "S-2";
        w.location = "DELHI";
        w.capacity = 100;
        w.stock = 40;

        repository.create(w);

        Warehouse updated = repository.findByBusinessUnitCode("S-2");
        updated.stock = 99;

        repository.update(updated);

        Warehouse check = repository.findByBusinessUnitCode("S-2");
        assertEquals(99, check.stock);
    }

    // ---------------- UPDATE NULL ----------------
    @Test
    void update_null_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.update(null));
    }

    // ---------------- UPDATE NOT FOUND ----------------
    @Test
    void update_not_found_shouldThrow() {

        Warehouse w = new Warehouse();
        w.businessUnitCode = "FAKE-1";
        w.location = "DELHI";

        assertThrows(IllegalArgumentException.class,
                () -> repository.update(w));
    }

    // ---------------- FIND NULL ----------------
    @Test
    void find_null_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.findByBusinessUnitCode(null));
    }

    // ---------------- FIND NOT EXIST ----------------
    @Test
    void find_not_exist_shouldReturnNull() {
        Warehouse w = repository.findByBusinessUnitCode("NOPE");
        assertNull(w);
    }

    // ---------------- REMOVE FLOW ----------------
    @Test
    @Transactional
    void remove_success() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "S-3";
        w.location = "DELHI";

        repository.create(w);

        repository.remove(w);

        Warehouse found = repository.findByBusinessUnitCode("S-3");
        assertNotNull(found);
    }

    // ---------------- REMOVE NULL ----------------
    @Test
    void remove_null_shouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.remove(null));
    }

    // ---------------- GET ALL ----------------
    @Test
    @Transactional
    void get_all_success() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "S-4";
        w.location = "DELHI";

        repository.create(w);

        assertFalse(repository.getAll().isEmpty());
    }
}