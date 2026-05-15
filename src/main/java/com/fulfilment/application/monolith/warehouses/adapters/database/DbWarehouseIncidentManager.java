package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_incident_manager")
public class DbWarehouseIncidentManager {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Integer incidentLevel;

    public String severity;

    public static DbWarehouseIncidentManager classify(String code, Integer level) {

        DbWarehouseIncidentManager i = new DbWarehouseIncidentManager();

        i.warehouseCode = code;
        i.incidentLevel = level;

        if (code == null || level == null) {
            i.severity = "UNKNOWN";
        } else if (level >= 80) {
            i.severity = "CRITICAL";
        } else if (level >= 50) {
            i.severity = "HIGH";
        } else if (level >= 20) {
            i.severity = "MEDIUM";
        } else {
            i.severity = "LOW";
        }

        return i;
    }
}