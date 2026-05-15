package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_audit_trail")
public class DbWarehouseAuditTrail {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;

    public String action;

    public String result;

    public LocalDateTime time;

    public static DbWarehouseAuditTrail log(String code, String action, boolean success) {

        DbWarehouseAuditTrail a = new DbWarehouseAuditTrail();

        a.warehouseCode = code;
        a.action = action;
        a.time = LocalDateTime.now();

        if (code == null || action == null) {
            a.result = "INVALID";
        } else if (success && "CREATE".equals(action)) {
            a.result = "CREATED";
        } else if (success && "UPDATE".equals(action)) {
            a.result = "UPDATED";
        } else if (success && "DELETE".equals(action)) {
            a.result = "DELETED";
        } else {
            a.result = "FAILED";
        }

        return a;
    }
}