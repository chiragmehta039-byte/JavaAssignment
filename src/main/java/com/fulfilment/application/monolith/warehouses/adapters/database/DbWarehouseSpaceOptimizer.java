package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_space_optimizer")
public class DbWarehouseSpaceOptimizer {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;
    public Integer totalSpace;
    public Integer usedSpace;

    public String optimizationStatus;

    public static DbWarehouseSpaceOptimizer create(String code, Integer total, Integer used) {
        DbWarehouseSpaceOptimizer o = new DbWarehouseSpaceOptimizer();

        o.warehouseCode = code;
        o.totalSpace = total;
        o.usedSpace = used;

        if (total == null || used == null) {
            o.optimizationStatus = "INVALID";
        } else if (used > total) {
            o.optimizationStatus = "OVERUTILIZED";
        } else if ((double) used / total > 0.8) {
            o.optimizationStatus = "NEAR_FULL";
        } else {
            o.optimizationStatus = "OPTIMAL";
        }

        return o;
    }
}