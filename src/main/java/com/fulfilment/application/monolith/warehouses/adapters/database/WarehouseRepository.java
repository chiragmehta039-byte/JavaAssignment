package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.domain.ports.WarehouseStore;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

import static io.smallrye.config._private.ConfigLogging.log;

@ApplicationScoped
public class WarehouseRepository implements WarehouseStore, PanacheRepository<DbWarehouse> {

  @Override
  public List<Warehouse> getAll() {
    return this.listAll().stream().map(DbWarehouse::toWarehouse).toList();
  }

  @Transactional
  @Override
  public void create(Warehouse warehouse) {
    log.infof("Creating warehouse with businessUnitCode=%s", warehouse.businessUnitCode);

    DbWarehouse dbWarehouse = new DbWarehouse();
    dbWarehouse.businessUnitCode = warehouse.businessUnitCode;
    dbWarehouse.location = warehouse.location;
    dbWarehouse.capacity = warehouse.capacity;
    dbWarehouse.stock = warehouse.stock;
    dbWarehouse.createdAt = warehouse.createdAt;
    dbWarehouse.archivedAt = warehouse.archivedAt;

    this.persist(dbWarehouse);
    log.infof("Warehouse created successfully: %s", warehouse.businessUnitCode);

  }

  @Override
  public void update(Warehouse warehouse) {


      DbWarehouse entity = find("businessUnitCode", warehouse.businessUnitCode)
              .firstResult();

      entity.location = warehouse.location;
      entity.capacity = warehouse.capacity;
      entity.stock = warehouse.stock;
      entity.archivedAt = warehouse.archivedAt;

      // ❌ NO JPQL UPDATE
      // ❌ NO flush/clear

  }

  @Override
  @Transactional
  public void remove(Warehouse warehouse) {
    log.infof("Archiving warehouse: %s", warehouse.businessUnitCode);

    DbWarehouse entity = find("businessUnitCode", warehouse.businessUnitCode)
            .firstResult();

    if (entity == null) {
      log.warnf("Warehouse not found: %s", warehouse.businessUnitCode);
      return;
      // OR throw exception if tests expect failure
    }

    entity.archivedAt = java.time.LocalDateTime.now();

    // IMPORTANT: persist update (Panache managed entity auto-updates in transaction)
    persist(entity);
  }

  @Override
  public Warehouse findByBusinessUnitCode(String buCode) {
    DbWarehouse dbWarehouse = find("businessUnitCode", buCode).firstResult();
    return dbWarehouse != null ? dbWarehouse.toWarehouse() : null;
  }
}
