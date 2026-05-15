package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_log")
public class DbWarehouseLog {

    @Id
    @GeneratedValue
    public Long id;

    public String message;

    public String level; // INFO, WARN, ERROR

    public LocalDateTime timestamp;

    public DbWarehouseLog() {}

    public static DbWarehouseLog create(String msg, String level) {
        DbWarehouseLog log = new DbWarehouseLog();
        log.message = msg;
        log.level = level;
        log.timestamp = LocalDateTime.now();

        if (msg == null || msg.isBlank()) {
            log.level = "ERROR";
        } else if ("WARN".equals(level)) {
            log.level = "WARN";
        } else if ("ERROR".equals(level)) {
            log.level = "ERROR";
        } else {
            log.level = "INFO";
        }

        return log;
    }
}