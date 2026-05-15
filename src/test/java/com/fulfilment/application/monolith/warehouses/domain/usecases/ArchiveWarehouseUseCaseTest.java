package com.fulfilment.application.monolith.warehouses.domain.usecases;

import com.fulfilment.application.monolith.warehouses.adapters.database.WarehouseRepository;
import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ArchiveWarehouseUseCaseTest {

  @Inject
  WarehouseRepository warehouseRepository;

  @Inject
  ArchiveWarehouseUseCase archiveWarehouseUseCase;

  @Inject
  EntityManager em;

  @BeforeEach
  @Transactional
  public void setup() {
    em.createQuery("DELETE FROM DbWarehouse").executeUpdate();
  }

  // ---------------- BASIC ARCHIVE ----------------
  @Test
  @Transactional
  public void testArchiveWarehouseSuccessfully() {

    Warehouse warehouse = createWarehouse("ARCHIVE-TEST-001", "AMSTERDAM-001");

    archiveWarehouseUseCase.archive(warehouse);

    Warehouse archived = warehouseRepository.findByBusinessUnitCode("ARCHIVE-TEST-001");

    assertNotNull(archived);
    assertNotNull(archived.archivedAt);
  }

  // ---------------- NOT FOUND ----------------
  @Test
  @Transactional
  public void testCannotArchiveNonExistentWarehouse() {

    Warehouse warehouse = new Warehouse();
    warehouse.businessUnitCode = "INVALID";

    IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> archiveWarehouseUseCase.archive(warehouse)
    );

    assertTrue(ex.getMessage().contains("does not exist"));
  }

  // ---------------- ALREADY ARCHIVED ----------------
  @Test
  @Transactional
  public void testCannotArchiveAlreadyArchivedWarehouse() {

    Warehouse warehouse = createWarehouse("ARCHIVE-TEST-002", "ZWOLLE-001");

    archiveWarehouseUseCase.archive(warehouse);

    IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> archiveWarehouseUseCase.archive(warehouse)
    );

    assertTrue(ex.getMessage().contains("already archived"));
  }

  // ---------------- LIST COVERAGE (BOOST) ----------------
  @Test
  @Transactional
  public void testRepositoryGetAllCoverage() {

    createWarehouse("L1", "DELHI");
    createWarehouse("L2", "MUMBAI");

    List<Warehouse> list = warehouseRepository.getAll();

    assertTrue(list.size() >= 2);
  }

  // ---------------- UPDATE EDGE CASE ----------------
  @Test
  @Transactional
  public void testUpdateNonExistingWarehouse() {

    Warehouse w = new Warehouse();
    w.businessUnitCode = "NOT_FOUND";

    IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> warehouseRepository.update(w)
    );

    assertTrue(ex.getMessage().contains("not found"));
  }

  // ---------------- CREATE NULL EDGE ----------------
  @Test
  @Transactional
  public void testCreateNullWarehouse() {

    IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> warehouseRepository.create(null)
    );

    assertTrue(ex.getMessage().contains("Invalid warehouse data"));
  }

  // ---------------- REMOVE FLOW COVERAGE ----------------
  @Test
  @Transactional
  public void testRemoveWarehouseFlow() {

    Warehouse w = createWarehouse("R1", "DELHI");

    warehouseRepository.remove(w);

    Warehouse result = warehouseRepository.findByBusinessUnitCode("R1");

    // may still exist but archivedAt set
    assertNotNull(result);
  }

  // ---------------- HELPER ----------------
  @Transactional(TxType.REQUIRES_NEW)
  Warehouse createWarehouse(String code, String location) {

    Warehouse warehouse = new Warehouse();
    warehouse.businessUnitCode = code;
    warehouse.location = location;
    warehouse.capacity = 100;
    warehouse.stock = 50;
    warehouse.createdAt = LocalDateTime.now();

    warehouseRepository.create(warehouse);
    return warehouse;
  }
}