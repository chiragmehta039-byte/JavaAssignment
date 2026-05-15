package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_audit_trail_ext")
public class DbWarehouseAuditTrailExtended {

    @Id @GeneratedValue
    public Long id;

    public String action;

    public String user;

    public String severity;

    public LocalDateTime time;

    public static DbWarehouseAuditTrailExtended create(String action, String user) {
        DbWarehouseAuditTrailExtended a = new DbWarehouseAuditTrailExtended();
        a.action = action;
        a.user = user;
        a.time = LocalDateTime.now();

        if (action == null) a.severity = "UNKNOWN";
        else if (action.equals("DELETE")) a.severity = "HIGH";
        else if (action.equals("UPDATE")) a.severity = "MEDIUM";
        else a.severity = "LOW";

        return a;
    }
}