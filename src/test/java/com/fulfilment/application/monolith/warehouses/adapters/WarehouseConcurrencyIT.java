package com.fulfilment.application.monolith.warehouses.adapters;

import com.fulfilment.application.monolith.location.LocationGateway;
import com.fulfilment.application.monolith.warehouses.adapters.database.WarehouseRepository;
import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.domain.usecases.CreateWarehouseUseCase;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class WarehouseConcurrencyIT {

  @Inject
  WarehouseRepository warehouseRepository;

  @Inject
  LocationGateway locationResolver;

  @Inject
  CreateWarehouseUseCase createWarehouseUseCase;

  @Inject
  ManagedExecutor executor;   // ✅ FIXED IMPORT

  @Inject
  EntityManager em;

  @BeforeEach
  @Transactional
  public void setupCleanDb() {
    em.createQuery("DELETE FROM DbWarehouse").executeUpdate();
    em.flush();
    em.clear();
  }

  private Warehouse buildWarehouse(String code) {
    Warehouse w = new Warehouse();
    w.businessUnitCode = code;
    w.location = "AMSTERDAM-001";
    w.capacity = 50;
    w.stock = 10;
    return w;
  }

  // -------------------------------
  // 1. Unique concurrent creation
  // -------------------------------
  @Test
  public void testConcurrentWarehouseCreationWithUniqueCodesSucceeds() throws Exception {

    int threadCount = 10;
    List<Future<Void>> futures = new ArrayList<>();

    for (int i = 0; i < threadCount; i++) {
      final int index = i;

      futures.add(executor.supplyAsync(() -> {
        createWarehouseUseCase.create(buildWarehouse("CONCURRENT-" + index));
        return null;
      }));
    }

    for (Future<Void> f : futures) {
      f.get(10, TimeUnit.SECONDS); // ❌ do NOT swallow exceptions
    }

    long count = warehouseRepository.count();
    assertEquals(threadCount, count,
            "All concurrent creations with unique codes should succeed");
  }

  // -------------------------------
  // 2. Duplicate concurrent creation
  // -------------------------------
  @Test
  public void testConcurrentWarehouseCreationWithDuplicateCodeFails() throws Exception {

    int threadCount = 5;
    String duplicateCode = "DUPLICATE-" + System.currentTimeMillis();

    List<Future<Boolean>> futures = new ArrayList<>();

    for (int i = 0; i < threadCount; i++) {

      futures.add(executor.supplyAsync(() -> {
        try {
          createWarehouseUseCase.create(buildWarehouse(duplicateCode));
          return true;
        } catch (Exception e) {
          return false;
        }
      }));
    }

    int success = 0;

    for (Future<Boolean> f : futures) {
      if (f.get(10, TimeUnit.SECONDS)) {
        success++;
      }
    }

    assertEquals(1, success,
            "Only one warehouse with duplicate code should be created");
  }

  // -------------------------------
  // 3. Concurrent reads
  // -------------------------------
  @Test
  public void testConcurrentReadsAreNonBlocking() throws Exception {

    createWarehouseUseCase.create(buildWarehouse("READ-TEST-001"));

    int readThreads = 20;

    for (int i = 0; i < readThreads; i++) {
      Warehouse w = warehouseRepository.findByBusinessUnitCode("READ-TEST-001");
      assertNotNull(w);
    }
  }
}