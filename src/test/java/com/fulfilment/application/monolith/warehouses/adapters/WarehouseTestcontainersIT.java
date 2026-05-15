package com.fulfilment.application.monolith.warehouses.adapters;

import com.fulfilment.application.monolith.location.LocationGateway;
import com.fulfilment.application.monolith.warehouses.adapters.database.DbWarehouse;
import com.fulfilment.application.monolith.warehouses.adapters.database.WarehouseRepository;
import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.domain.usecases.CreateWarehouseUseCase;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class WarehouseTestcontainersIT {

  @Inject WarehouseRepository warehouseRepository;
  @Inject LocationGateway locationResolver;
  @Inject EntityManager em;

  private CreateWarehouseUseCase createWarehouseUseCase;

  @BeforeEach
  @Transactional
  public void setup() {
    em.createQuery("DELETE FROM DbWarehouse").executeUpdate();
    createWarehouseUseCase = new CreateWarehouseUseCase(warehouseRepository, locationResolver);
  }

  // ---------------- UNIQUE CONSTRAINT ----------------
  @Test
  @Transactional
  public void testDatabaseUniqueConstraintOnBusinessUnitCode() {

    Warehouse w1 = new Warehouse();
    w1.businessUnitCode = "DB-UNIQUE-001";
    w1.location = "AMSTERDAM-001";
    w1.capacity = 50;
    w1.stock = 10;

    createWarehouseUseCase.create(w1);

    DbWarehouse duplicate = new DbWarehouse();
    duplicate.businessUnitCode = "DB-UNIQUE-001";
    duplicate.location = "ZWOLLE-001";
    duplicate.capacity = 30;
    duplicate.stock = 5;

    assertThrows(Exception.class, () -> {
      em.persist(duplicate);
      em.flush();
    });
  }

  // ---------------- MULTI DATA SCENARIO ----------------
  @Test
  @Transactional
  public void testQueryingMultipleWarehousesAtSameLocation() {

    for (int i = 0; i < 6; i++) {
      Warehouse w = new Warehouse();
      w.businessUnitCode = "QUERY-" + i;
      w.location = "AMSTERDAM-001";
      w.capacity = 20 + i;
      w.stock = 5 + i;
      createWarehouseUseCase.create(w);
    }

    List<Warehouse> all = warehouseRepository.getAll();

    long count = all.stream()
            .filter(w -> "AMSTERDAM-001".equals(w.location))
            .count();

    assertTrue(count >= 5);
  }

  // ---------------- NULL EDGE CASE ----------------
  @Test
  @Transactional
  public void testNullFieldsHandling() {

    DbWarehouse db = new DbWarehouse();
    db.businessUnitCode = "NULL-TEST";
    db.location = "ZWOLLE-001";
    db.capacity = 50;
    db.stock = 10;
    db.createdAt = java.time.LocalDateTime.now();
    db.archivedAt = null;

    em.persist(db);
    em.flush();

    DbWarehouse found = em.find(DbWarehouse.class, db.id);

    assertNotNull(found);
    assertNull(found.archivedAt);
  }

  // ---------------- ROLLBACK ----------------
  @Test
  public void testTransactionRollbackDoesNotPersist() {

    try {
      failTransaction();
    } catch (Exception ignored) {}

    Warehouse found = warehouseRepository.findByBusinessUnitCode("ROLLBACK-001");

    assertNull(found);
  }

  @Transactional
  void failTransaction() {
    Warehouse w = new Warehouse();
    w.businessUnitCode = "ROLLBACK-001";
    w.location = "TILBURG-001";
    w.capacity = 20;
    w.stock = 5;

    createWarehouseUseCase.create(w);

    throw new RuntimeException("force rollback");
  }

  // ---------------- COMPLEX QUERY ----------------
  @Test
  @Transactional
  public void testComplexQueryByLocationAndCapacity() {

    create("C1", "AMSTERDAM-001", 30);
    create("C2", "AMSTERDAM-001", 50);
    create("C3", "AMSTERDAM-001", 70);
    create("C4", "ZWOLLE-001", 40);

    List<DbWarehouse> results = em.createQuery(
                    "SELECT w FROM DbWarehouse w WHERE w.location = :loc AND w.capacity BETWEEN :min AND :max",
                    DbWarehouse.class)
            .setParameter("loc", "AMSTERDAM-001")
            .setParameter("min", 40)
            .setParameter("max", 70)
            .getResultList();

    assertEquals(2, results.size());
  }

  // ---------------- HELPER ----------------
  private void create(String code, String location, int capacity) {
    Warehouse w = new Warehouse();
    w.businessUnitCode = code;
    w.location = location;
    w.capacity = capacity;
    w.stock = 10;
    createWarehouseUseCase.create(w);
  }
}