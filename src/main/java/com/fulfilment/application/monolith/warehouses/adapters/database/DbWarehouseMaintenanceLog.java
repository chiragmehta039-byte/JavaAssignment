package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_maintenance_log")
public class DbWarehouseMaintenanceLog {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;
    public String type;
    public Boolean completed;

    public LocalDateTime logTime;

    public String status;

    public static DbWarehouseMaintenanceLog create(String code, String type, Boolean completed) {

        DbWarehouseMaintenanceLog l = new DbWarehouseMaintenanceLog();

        l.warehouseCode = code;
        l.type = type;
        l.completed = completed;
        l.logTime = LocalDateTime.now();

        if (code == null) {
            l.status = "INVALID";
        } else if (Boolean.TRUE.equals(completed)) {
            l.status = "DONE";
        } else if ("URGENT".equals(type)) {
            l.status = "PENDING_CRITICAL";
        } else {
            l.status = "PENDING";
        }

        return l;
    }
}