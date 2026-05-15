package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_health_monitor")
public class DbWarehouseHealthMonitor {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Integer temperature;

    public Integer humidity;

    public String healthStatus;

    public LocalDateTime checkedAt;

    public static DbWarehouseHealthMonitor evaluate(String code, Integer temp, Integer humidity) {
        DbWarehouseHealthMonitor h = new DbWarehouseHealthMonitor();

        h.warehouseCode = code;
        h.temperature = temp;
        h.humidity = humidity;
        h.checkedAt = LocalDateTime.now();

        if (code == null) {
            h.healthStatus = "INVALID";
        } else if (temp == null || humidity == null) {
            h.healthStatus = "UNKNOWN";
        } else if (temp > 40 || humidity > 80) {
            h.healthStatus = "CRITICAL";
        } else if (temp > 30 || humidity > 60) {
            h.healthStatus = "WARNING";
        } else {
            h.healthStatus = "NORMAL";
        }

        return h;
    }
}