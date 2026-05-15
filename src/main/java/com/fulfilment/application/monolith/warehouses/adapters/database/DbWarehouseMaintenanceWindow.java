package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_maintenance_window")
public class DbWarehouseMaintenanceWindow {

    @Id @GeneratedValue
    public Long id;

    public String type;
    public Integer durationHours;
    public String status;

    public LocalDateTime scheduledAt;

    public static DbWarehouseMaintenanceWindow create(String type, Integer durationHours) {
        DbWarehouseMaintenanceWindow m = new DbWarehouseMaintenanceWindow();
        m.type = type;
        m.durationHours = durationHours;
        m.scheduledAt = LocalDateTime.now();

        if (durationHours == null || durationHours <= 0) {
            m.status = "INVALID";
        } else if ("EMERGENCY".equals(type)) {
            m.status = "URGENT";
        } else if (durationHours > 5) {
            m.status = "LONG";
        } else {
            m.status = "SHORT";
        }

        return m;
    }
}