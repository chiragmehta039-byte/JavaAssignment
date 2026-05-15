package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.adapters.database.WarehouseRepository;
import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class WarehouseRepositoryTest {

    @Inject
    WarehouseRepository repository;

    @Test
    @Transactional
    void shouldCreateAndFetchWarehouse() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "DB-001";
        w.location = "DELHI";
        w.capacity = 100;
        w.stock = 50;

        repository.create(w);

        Warehouse result = repository.findByBusinessUnitCode("DB-001");

        assertNotNull(result);
        assertEquals("DB-001", result.businessUnitCode);
    }

    @Test
    @Transactional
    void shouldReturnAllWarehouses() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "DB-002";
        w.location = "MUMBAI";
        w.capacity = 200;
        w.stock = 80;

        repository.create(w);

        List<Warehouse> list = repository.getAll();

        assertFalse(list.isEmpty());
    }

    @Test
    @Transactional
    void shouldUpdateWarehouse() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "DB-003";
        w.location = "DELHI";
        w.capacity = 100;
        w.stock = 20;

        repository.create(w);

        w.stock = 90;
        repository.update(w);

        Warehouse updated = repository.findByBusinessUnitCode("DB-003");

        assertEquals(90, updated.stock);
    }

    @Test
    @Transactional
    void shouldRemoveWarehouse() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "DB-004";
        w.location = "DELHI";

        repository.create(w);

        repository.remove(w);

        Warehouse result = repository.findByBusinessUnitCode("DB-004");

        assertNotNull(result); // archivedAt should be set, not deleted
        assertNotNull(result.archivedAt);
    }
}