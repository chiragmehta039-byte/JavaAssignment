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
    try {
      return this.listAll().stream()
              .map(DbWarehouse::toWarehouse)
              .toList();
    } catch (NullPointerException e) {
      log.error("Error while fetching warehouses", e);
      throw new RuntimeException("Failed to fetch warehouses");
    }
  }

  @Transactional
  @Override
  public void create(Warehouse warehouse) {
    try {
      if (warehouse == null) {
        throw new NullPointerException("Warehouse is null");
      }

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

    } catch (NullPointerException e) {
      log.error("Null pointer while creating warehouse", e);
      throw new IllegalArgumentException("Invalid warehouse data");
    }
  }

  @Override
  @Transactional
  public void update(Warehouse warehouse) {
    try {
      if (warehouse == null) {
        throw new NullPointerException("Warehouse is null");
      }

      DbWarehouse entity = find("businessUnitCode", warehouse.businessUnitCode)
              .firstResult();

      if (entity == null) {
        throw new IllegalArgumentException("Warehouse not found");
      }

      entity.location = warehouse.location;
      entity.capacity = warehouse.capacity;
      entity.stock = warehouse.stock;
      entity.archivedAt = warehouse.archivedAt;

    } catch (NullPointerException e) {
      log.error("Null pointer while updating warehouse", e);
      throw new IllegalArgumentException("Invalid warehouse data");
    }
  }

  @Override
  @Transactional
  public void remove(Warehouse warehouse) {

    try {
      if (warehouse == null) {
        throw new NullPointerException("Warehouse is null");
      }

      log.infof("Archiving warehouse: %s", warehouse.businessUnitCode);

      DbWarehouse entity = find("businessUnitCode", warehouse.businessUnitCode)
              .firstResult();

      if (entity == null) {
        log.warnf("Warehouse not found: %s", warehouse.businessUnitCode);
        return;
      }

      entity.archivedAt = java.time.LocalDateTime.now();

    } catch (NullPointerException e) {
      log.error("Null pointer while removing warehouse", e);
      throw new IllegalArgumentException("Invalid warehouse data");
    }
  }

  @Override
  public Warehouse findByBusinessUnitCode(String buCode) {

    try {
      if (buCode == null) {
        throw new NullPointerException("BusinessUnitCode is null");
      }

      DbWarehouse dbWarehouse = find("businessUnitCode", buCode).firstResult();

      return dbWarehouse != null ? dbWarehouse.toWarehouse() : null;

    } catch (NullPointerException e) {
      log.error("Null pointer while finding warehouse", e);
      throw new IllegalArgumentException("Invalid business unit code");
    }
  }
}