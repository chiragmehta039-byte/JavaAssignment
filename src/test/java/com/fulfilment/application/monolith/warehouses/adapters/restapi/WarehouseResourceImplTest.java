package com.fulfilment.application.monolith.warehouses.adapters.restapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fulfilment.application.monolith.warehouses.adapters.database.WarehouseRepository;
import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.domain.ports.ArchiveWarehouseOperation;
import com.fulfilment.application.monolith.warehouses.domain.ports.CreateWarehouseOperation;
import com.fulfilment.application.monolith.warehouses.domain.ports.ReplaceWarehouseOperation;

import jakarta.ws.rs.WebApplicationException;
import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WarehouseResourceImplTest {

    private WarehouseResourceImpl resource;

    private WarehouseRepository repository;
    private CreateWarehouseOperation createOperation;
    private ArchiveWarehouseOperation archiveOperation;
    private ReplaceWarehouseOperation replaceOperation;

    @BeforeEach
    void setup() {

        resource = new WarehouseResourceImpl();

        repository = mock(WarehouseRepository.class);
        createOperation = mock(CreateWarehouseOperation.class);
        archiveOperation = mock(ArchiveWarehouseOperation.class);
        replaceOperation = mock(ReplaceWarehouseOperation.class);

        inject(resource, "warehouseRepository", repository);
        inject(resource, "createWarehouseOperation", createOperation);
        inject(resource, "archiveWarehouseOperation", archiveOperation);
        inject(resource, "replaceWarehouseOperation", replaceOperation);
    }

    @Test
    void shouldReturnAllWarehouses() {

        Warehouse warehouse = new Warehouse();
        warehouse.businessUnitCode = "WH-001";
        warehouse.location = "Delhi";

        when(repository.getAll()).thenReturn(List.of(warehouse));

        var result = resource.listAllWarehousesUnits();

        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnEmptyWarehouseList() {

        when(repository.getAll()).thenReturn(List.of());

        var result = resource.listAllWarehousesUnits();

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldGetWarehouseById() {

        Warehouse warehouse = new Warehouse();
        warehouse.businessUnitCode = "WH-001";
        warehouse.location = "Delhi";
        warehouse.capacity = 100;
        warehouse.stock = 50;

        when(repository.findByBusinessUnitCode("WH-001"))
                .thenReturn(warehouse);

        var result = resource.getAWarehouseUnitByID("WH-001");

        assertEquals("WH-001", result.getBusinessUnitCode());
        assertEquals("Delhi", result.getLocation());
    }

    @Test
    void shouldThrow404WhenWarehouseNotFound() {

        when(repository.findByBusinessUnitCode("INVALID"))
                .thenReturn(null);

        assertThrows(WebApplicationException.class, () ->
                resource.getAWarehouseUnitByID("INVALID"));
    }

    @Test
    void shouldCreateWarehouseSuccessfully() {

        com.warehouse.api.beans.Warehouse request =
                new com.warehouse.api.beans.Warehouse();

        request.setBusinessUnitCode("WH-001");
        request.setLocation("Delhi");
        request.setCapacity(100);
        request.setStock(50);

        var response = resource.createANewWarehouseUnit(request);

        assertEquals("WH-001", response.getBusinessUnitCode());
        assertEquals("Delhi", response.getLocation());

        verify(createOperation).create(any());
    }

    @Test
    void shouldCreateWarehouseWithNullStock() {

        com.warehouse.api.beans.Warehouse request =
                new com.warehouse.api.beans.Warehouse();

        request.setBusinessUnitCode("WH-001");
        request.setLocation("Delhi");
        request.setCapacity(100);
        request.setStock(null);

        var response = resource.createANewWarehouseUnit(request);

        assertEquals(0, response.getStock());

        verify(createOperation).create(any());
    }

    @Test
    void shouldThrow400WhenCreateFails() {

        com.warehouse.api.beans.Warehouse request =
                new com.warehouse.api.beans.Warehouse();

        doThrow(new IllegalArgumentException("Invalid"))
                .when(createOperation)
                .create(any());

        assertThrows(WebApplicationException.class, () ->
                resource.createANewWarehouseUnit(request));
    }

    @Test
    void shouldArchiveWarehouse() {

        Warehouse warehouse = new Warehouse();
        warehouse.businessUnitCode = "WH-001";

        when(repository.findByBusinessUnitCode("WH-001"))
                .thenReturn(warehouse);

        resource.archiveAWarehouseUnitByID("WH-001");

        verify(archiveOperation).archive(any());
    }

    @Test
    void shouldThrow404WhenArchiveWarehouseNotFound() {

        when(repository.findByBusinessUnitCode("INVALID"))
                .thenReturn(null);

        assertThrows(WebApplicationException.class, () ->
                resource.archiveAWarehouseUnitByID("INVALID"));
    }

    @Test
    void shouldThrow400WhenArchiveFails() {

        Warehouse warehouse = new Warehouse();
        warehouse.businessUnitCode = "WH-001";

        when(repository.findByBusinessUnitCode("WH-001"))
                .thenReturn(warehouse);

        doThrow(new IllegalArgumentException("Archive failed"))
                .when(archiveOperation)
                .archive(any());

        assertThrows(WebApplicationException.class, () ->
                resource.archiveAWarehouseUnitByID("WH-001"));
    }

    @Test
    void shouldReplaceWarehouseSuccessfully() {

        com.warehouse.api.beans.Warehouse request =
                new com.warehouse.api.beans.Warehouse();

        request.setLocation("Mumbai");
        request.setCapacity(500);
        request.setStock(100);

        Warehouse updated = new Warehouse();
        updated.businessUnitCode = "WH-001";
        updated.location = "Mumbai";
        updated.capacity = 500;
        updated.stock = 100;

        when(repository.findByBusinessUnitCode("WH-001"))
                .thenReturn(updated);

        var response = resource.replaceTheCurrentActiveWarehouse(
                "WH-001",
                request);

        assertEquals("Mumbai", response.getLocation());

        verify(replaceOperation).replace(any());
    }

    @Test
    void shouldReplaceWarehouseWithNullStock() {

        com.warehouse.api.beans.Warehouse request =
                new com.warehouse.api.beans.Warehouse();

        request.setLocation("Mumbai");
        request.setCapacity(500);
        request.setStock(null);

        Warehouse updated = new Warehouse();
        updated.businessUnitCode = "WH-001";
        updated.stock = 0;

        when(repository.findByBusinessUnitCode("WH-001"))
                .thenReturn(updated);

        var response = resource.replaceTheCurrentActiveWarehouse(
                "WH-001",
                request);

        assertEquals(0, response.getStock());
    }

    @Test
    void shouldThrow400WhenReplaceFails() {

        com.warehouse.api.beans.Warehouse request =
                new com.warehouse.api.beans.Warehouse();

        doThrow(new IllegalArgumentException("Replace failed"))
                .when(replaceOperation)
                .replace(any());

        assertThrows(WebApplicationException.class, () ->
                resource.replaceTheCurrentActiveWarehouse(
                        "WH-001",
                        request));
    }

    @Test
    void shouldSearchWarehousesByLocation() {

        Warehouse warehouse1 = new Warehouse();
        warehouse1.businessUnitCode = "WH-001";
        warehouse1.location = "Delhi";
        warehouse1.capacity = 100;

        Warehouse warehouse2 = new Warehouse();
        warehouse2.businessUnitCode = "WH-002";
        warehouse2.location = "Mumbai";
        warehouse2.capacity = 200;

        when(repository.getAll())
                .thenReturn(List.of(warehouse1, warehouse2));

        var result = resource.searchWarehouses(
                "Delhi",
                null,
                null,
                null,
                null,
                null,
                null);

        assertEquals(1, result.size());
    }

    @Test
    void shouldSearchWarehousesByMinCapacity() {

        Warehouse warehouse1 = new Warehouse();
        warehouse1.capacity = 100;

        Warehouse warehouse2 = new Warehouse();
        warehouse2.capacity = 500;

        when(repository.getAll())
                .thenReturn(List.of(warehouse1, warehouse2));

        var result = resource.searchWarehouses(
                null,
                BigInteger.valueOf(200),
                null,
                null,
                null,
                null,
                null);

        assertEquals(1, result.size());
    }

    @Test
    void shouldSearchWarehousesByMaxCapacity() {

        Warehouse warehouse1 = new Warehouse();
        warehouse1.capacity = 100;

        Warehouse warehouse2 = new Warehouse();
        warehouse2.capacity = 500;

        when(repository.getAll())
                .thenReturn(List.of(warehouse1, warehouse2));

        var result = resource.searchWarehouses(
                null,
                null,
                BigInteger.valueOf(200),
                null,
                null,
                null,
                null);

        assertEquals(1, result.size());
    }

    @Test
    void shouldSortWarehousesByCapacityDesc() {

        Warehouse warehouse1 = new Warehouse();
        warehouse1.capacity = 100;

        Warehouse warehouse2 = new Warehouse();
        warehouse2.capacity = 500;

        when(repository.getAll())
                .thenReturn(List.of(warehouse1, warehouse2));

        var result = resource.searchWarehouses(
                null,
                null,
                null,
                "capacity",
                "desc",
                null,
                null);

        assertEquals(500, result.get(0).getCapacity());
    }

    @Test
    void shouldSortWarehousesByLocation() {

        Warehouse warehouse1 = new Warehouse();
        warehouse1.location = "Mumbai";

        Warehouse warehouse2 = new Warehouse();
        warehouse2.location = "Delhi";

        when(repository.getAll())
                .thenReturn(List.of(warehouse1, warehouse2));

        var result = resource.searchWarehouses(
                null,
                null,
                null,
                "location",
                "asc",
                null,
                null);

        assertEquals("Delhi", result.get(0).getLocation());
    }

    @Test
    void shouldThrow400ForInvalidSortField() {

        when(repository.getAll())
                .thenReturn(List.of());

        assertThrows(WebApplicationException.class, () ->
                resource.searchWarehouses(
                        null,
                        null,
                        null,
                        "invalid",
                        "asc",
                        null,
                        null));
    }

    @Test
    void shouldPaginateResults() {

        Warehouse warehouse1 = new Warehouse();
        warehouse1.businessUnitCode = "WH-001";

        Warehouse warehouse2 = new Warehouse();
        warehouse2.businessUnitCode = "WH-002";

        when(repository.getAll())
                .thenReturn(List.of(warehouse1, warehouse2));

        var result = resource.searchWarehouses(
                null,
                null,
                null,
                null,
                null,
                BigInteger.ZERO,
                BigInteger.ONE);

        assertEquals(1, result.size());
    }

    @Test
    void shouldThrow400ForInvalidPagination() {

        when(repository.getAll())
                .thenReturn(List.of());

        assertThrows(WebApplicationException.class, () ->
                resource.searchWarehouses(
                        null,
                        null,
                        null,
                        null,
                        null,
                        BigInteger.valueOf(-1),
                        BigInteger.ZERO));
    }

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