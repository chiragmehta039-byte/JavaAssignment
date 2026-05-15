package com.fulfilment.application.monolith.warehouses.adapters;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.domain.ports.WarehouseStore;
import com.fulfilment.application.monolith.warehouses.domain.usecases.ArchiveWarehouseUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArchiveWarehouseUseCaseTest {

    private WarehouseStore warehouseStore;
    private ArchiveWarehouseUseCase useCase;

    @BeforeEach
    void setup() {
        warehouseStore = mock(WarehouseStore.class);
        useCase = new ArchiveWarehouseUseCase(warehouseStore);
    }

    private Warehouse baseWarehouse() {
        Warehouse w = new Warehouse();
        w.businessUnitCode = "WH-001";
        w.location = "DELHI";
        w.capacity = 100;
        w.stock = 50;
        return w;
    }

    // ---------------- NOT FOUND CASE ----------------
    @Test
    void shouldThrowWhenWarehouseNotFound() {

        Warehouse input = baseWarehouse();

        when(warehouseStore.findByBusinessUnitCode("WH-001"))
                .thenReturn(null);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.archive(input)
        );

        assertTrue(ex.getMessage().contains("does not exist"));
    }

    // ---------------- ALREADY ARCHIVED CASE ----------------
    @Test
    void shouldThrowWhenAlreadyArchived() {

        Warehouse existing = baseWarehouse();
        existing.archivedAt = LocalDateTime.now();

        when(warehouseStore.findByBusinessUnitCode("WH-001"))
                .thenReturn(existing);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.archive(existing)
        );

        assertTrue(ex.getMessage().contains("already archived"));
    }

    // ---------------- SUCCESS CASE ----------------
    @Test
    void shouldArchiveSuccessfully() {

        Warehouse existing = baseWarehouse();

        when(warehouseStore.findByBusinessUnitCode("WH-001"))
                .thenReturn(existing);

        useCase.archive(existing);

        assertNotNull(existing.archivedAt);

        verify(warehouseStore, times(1)).update(existing);
    }

    // ---------------- EDGE CASE (NULL SAFE INPUT) ----------------
    @Test
    void shouldHandleSameObjectReferenceCorrectly() {

        Warehouse w = baseWarehouse();

        when(warehouseStore.findByBusinessUnitCode("WH-001"))
                .thenReturn(w);

        assertDoesNotThrow(() -> useCase.archive(w));

        verify(warehouseStore).update(w);
    }
}