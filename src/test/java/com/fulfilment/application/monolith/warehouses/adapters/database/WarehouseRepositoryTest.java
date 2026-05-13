package com.fulfilment.application.monolith.warehouses.adapters.database;

import static org.junit.jupiter.api.Assertions.*;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

@QuarkusTest
class WarehouseRepositoryTest {

    @Inject
    WarehouseRepository repository;

    @Test
    @Transactional
    void shouldCreateWarehouse() {

        Warehouse warehouse = new Warehouse();
        warehouse.businessUnitCode = "WH-001";
        warehouse.location = "Delhi";
        warehouse.capacity = 100;
        warehouse.stock = 50;
        warehouse.createdAt = LocalDateTime.now();

        repository.create(warehouse);

        Warehouse result =
                repository.findByBusinessUnitCode("WH-001");

        assertNotNull(result);
        assertEquals("Delhi", result.location);
    }

    @Test
    @Transactional
    void shouldReturnAllWarehouses() {

        Warehouse warehouse = new Warehouse();
        warehouse.businessUnitCode = "WH-002";
        warehouse.location = "Mumbai";

        repository.create(warehouse);

        var warehouses = repository.getAll();

        assertFalse(warehouses.isEmpty());
    }

    @Test
    @Transactional
    void shouldUpdateWarehouse() {

        Warehouse warehouse = new Warehouse();
        warehouse.businessUnitCode = "WH-003";
        warehouse.location = "Pune";

        repository.create(warehouse);

        warehouse.location = "Bangalore";

        repository.update(warehouse);

        Warehouse updated =
                repository.findByBusinessUnitCode("WH-003");

        assertEquals("Bangalore", updated.location);
    }

    @Test
    @Transactional
    void shouldArchiveWarehouse() {

        Warehouse warehouse = new Warehouse();
        warehouse.businessUnitCode = "WH-004";

        repository.create(warehouse);

        repository.remove(warehouse);

        Warehouse archived =
                repository.findByBusinessUnitCode("WH-004");

        assertNotNull(archived.archivedAt);
    }

    @Test
    @Transactional
    void shouldReturnNullWhenWarehouseNotFound() {

        Warehouse warehouse =
                repository.findByBusinessUnitCode("INVALID");

        assertNull(warehouse);
    }
}