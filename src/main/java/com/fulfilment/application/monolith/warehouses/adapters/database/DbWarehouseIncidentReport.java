package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_incident_report")
public class DbWarehouseIncidentReport {

    @Id @GeneratedValue
    public Long id;

    public String type;
    public Integer severity;
    public String outcome;

    public static DbWarehouseIncidentReport create(String type, Integer severity) {
        DbWarehouseIncidentReport r = new DbWarehouseIncidentReport();
        r.type = type;
        r.severity = severity;

        if (severity == null) r.outcome = "UNKNOWN";
        else if (severity > 80) r.outcome = "CRITICAL";
        else if (severity > 40) r.outcome = "MAJOR";
        else r.outcome = "MINOR";

        return r;
    }
}