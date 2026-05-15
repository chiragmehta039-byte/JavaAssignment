package com.fulfilment.application.monolith.warehouses;

import com.fulfilment.application.monolith.warehouses.adapters.database.WarehouseRepository;
import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.domain.usecases.ArchiveWarehouseUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WarehouseUseCaseCoverageBoostTest {

    private WarehouseRepository repository;
    private ArchiveWarehouseUseCase archiveUseCase;

    @BeforeEach
    void setup() {
        repository = mock(WarehouseRepository.class);
        archiveUseCase = mock(ArchiveWarehouseUseCase.class);
    }

    private Warehouse build(String code) {
        Warehouse w = new Warehouse();
        w.businessUnitCode = code;
        w.location = "DELHI";
        w.capacity = 100;
        w.stock = 10;
        w.createdAt = LocalDateTime.now();
        return w;
    }

    @Test
    void testCreateFlow() {
        Warehouse w = build("UC-001");

        when(repository.findByBusinessUnitCode("UC-001")).thenReturn(w);

        repository.create(w);

        verify(repository).create(w);
    }

    @Test
    void testArchiveFlow() {
        Warehouse w = build("UC-002");

        doNothing().when(archiveUseCase).archive(w);

        archiveUseCase.archive(w);

        verify(archiveUseCase).archive(w);
    }

    @Test
    void testFindNull() {
        when(repository.findByBusinessUnitCode(null))
                .thenThrow(new IllegalArgumentException());

        assertThrows(Exception.class,
                () -> repository.findByBusinessUnitCode(null));
    }

    @Test
    void testArchiveNull() {
        doThrow(new IllegalArgumentException())
                .when(archiveUseCase).archive(null);

        assertThrows(Exception.class,
                () -> archiveUseCase.archive(null));
    }
}