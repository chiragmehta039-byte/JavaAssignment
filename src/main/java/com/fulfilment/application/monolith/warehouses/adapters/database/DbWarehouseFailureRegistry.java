package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_failure_registry")
public class DbWarehouseFailureRegistry {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;
    public String failureType;
    public Integer severity;

    public String resolutionStatus;

    public static DbWarehouseFailureRegistry create(String code, String type, Integer severity) {

        DbWarehouseFailureRegistry f = new DbWarehouseFailureRegistry();

        f.warehouseCode = code;
        f.failureType = type;
        f.severity = severity;

        if (code == null || type == null) {
            f.resolutionStatus = "INVALID";
        } else if (severity == null) {
            f.resolutionStatus = "UNKNOWN";
        } else if (severity > 80) {
            f.resolutionStatus = "CRITICAL";
        } else if (severity > 40) {
            f.resolutionStatus = "MAJOR";
        } else {
            f.resolutionStatus = "MINOR";
        }

        return f;
    }
}