package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_utilization_log")
public class DbWarehouseUtilizationLog {

    @Id @GeneratedValue
    public Long id;

    public Integer used;
    public Integer total;

    public String level;

    public LocalDateTime time;

    public static DbWarehouseUtilizationLog create(Integer used, Integer total) {
        DbWarehouseUtilizationLog l = new DbWarehouseUtilizationLog();
        l.used = used;
        l.total = total;
        l.time = LocalDateTime.now();

        if (used == null || total == null) l.level = "UNKNOWN";
        else if (used > total) l.level = "OVER";
        else if (used.equals(total)) l.level = "FULL";
        else l.level = "OK";

        return l;
    }
}