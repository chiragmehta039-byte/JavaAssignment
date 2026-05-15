package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_utilization_engine")
public class DbWarehouseUtilizationEngine {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Integer used;

    public Integer total;

    public String utilizationStatus;

    public static DbWarehouseUtilizationEngine calculate(String code, Integer used, Integer total) {

        DbWarehouseUtilizationEngine u = new DbWarehouseUtilizationEngine();

        u.warehouseCode = code;
        u.used = used;
        u.total = total;

        if (code == null || used == null || total == null) {
            u.utilizationStatus = "INVALID";
        } else if (total == 0) {
            u.utilizationStatus = "ERROR";
        } else {
            double ratio = (double) used / total;

            if (ratio > 0.9) {
                u.utilizationStatus = "CRITICAL";
            } else if (ratio > 0.7) {
                u.utilizationStatus = "WARNING";
            } else {
                u.utilizationStatus = "NORMAL";
            }
        }

        return u;
    }
}