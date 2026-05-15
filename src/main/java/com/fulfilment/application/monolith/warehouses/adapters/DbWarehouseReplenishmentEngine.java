package com.fulfilment.application.monolith.warehouses.adapters;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_replenishment_engine")
public class DbWarehouseReplenishmentEngine {

    @Id
    @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Integer currentStock;

    public Integer minThreshold;

    public Integer maxCapacity;

    public String recommendation;

    public String priority;

    public LocalDateTime evaluatedAt;

    public static DbWarehouseReplenishmentEngine evaluate(
            String code,
            Integer stock,
            Integer minThreshold,
            Integer maxCapacity
    ) {

        DbWarehouseReplenishmentEngine r = new DbWarehouseReplenishmentEngine();

        r.warehouseCode = code;
        r.currentStock = stock;
        r.minThreshold = minThreshold;
        r.maxCapacity = maxCapacity;
        r.evaluatedAt = LocalDateTime.now();

        // ===== Validation Layer =====
        if (code == null || code.isBlank()) {
            r.recommendation = "INVALID";
            r.priority = "NONE";
            return r;
        }

        if (stock == null || minThreshold == null || maxCapacity == null) {
            r.recommendation = "DATA_MISSING";
            r.priority = "LOW";
            return r;
        }

        if (stock < 0 || minThreshold < 0 || maxCapacity <= 0) {
            r.recommendation = "INVALID_VALUES";
            r.priority = "LOW";
            return r;
        }

        // ===== Business Logic Layer =====
        if (stock < minThreshold) {
            r.recommendation = "RESTOCK_IMMEDIATELY";
            r.priority = "HIGH";

        } else if (stock < (minThreshold * 2)) {
            r.recommendation = "PLAN_REPLENISHMENT";
            r.priority = "MEDIUM";

        } else if (stock > maxCapacity) {
            r.recommendation = "REDUCE_STOCK";
            r.priority = "HIGH";

        } else if (stock.equals(maxCapacity)) {
            r.recommendation = "CAPACITY_FULL";
            r.priority = "MEDIUM";

        } else {
            r.recommendation = "STABLE";
            r.priority = "LOW";
        }

        return r;
    }
}