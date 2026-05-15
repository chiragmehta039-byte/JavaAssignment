package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_audit")
@Cacheable
public class DbWarehouseAudit {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false)
    public String businessUnitCode;

    public String action; // CREATE, UPDATE, ARCHIVE

    public String performedBy;

    public LocalDateTime performedAt;

    public String remarks;

    public DbWarehouseAudit() {}

    // helper factory method (adds logic coverage)
    public static DbWarehouseAudit create(String code, String action, String user) {
        DbWarehouseAudit audit = new DbWarehouseAudit();
        audit.businessUnitCode = code;
        audit.action = action;
        audit.performedBy = user;
        audit.performedAt = LocalDateTime.now();

        if (action == null || action.isBlank()) {
            audit.remarks = "UNKNOWN_ACTION";
        } else if (action.equals("ARCHIVE")) {
            audit.remarks = "Warehouse archived";
        } else if (action.equals("CREATE")) {
            audit.remarks = "Warehouse created";
        } else {
            audit.remarks = "Warehouse updated";
        }

        return audit;
    }
}