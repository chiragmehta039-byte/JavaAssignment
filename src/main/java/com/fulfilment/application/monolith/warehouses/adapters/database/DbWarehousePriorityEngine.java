package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_priority_engine")
public class DbWarehousePriorityEngine {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Integer priorityScore;

    public String priorityLevel;

    public static DbWarehousePriorityEngine evaluate(String code, Integer score) {

        DbWarehousePriorityEngine p = new DbWarehousePriorityEngine();

        p.warehouseCode = code;
        p.priorityScore = score;

        if (code == null) {
            p.priorityLevel = "INVALID";
        } else if (score == null) {
            p.priorityLevel = "UNKNOWN";
        } else if (score >= 80) {
            p.priorityLevel = "URGENT";
        } else if (score >= 50) {
            p.priorityLevel = "HIGH";
        } else if (score >= 20) {
            p.priorityLevel = "MEDIUM";
        } else {
            p.priorityLevel = "LOW";
        }

        return p;
    }
}