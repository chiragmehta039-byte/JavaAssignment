package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_cold_storage")
public class DbWarehouseColdStorageLog {

    @Id @GeneratedValue
    public Long id;

    public Integer temp;
    public String status;

    public static DbWarehouseColdStorageLog create(Integer temp) {
        DbWarehouseColdStorageLog c = new DbWarehouseColdStorageLog();
        c.temp = temp;

        if (temp == null) c.status = "UNKNOWN";
        else if (temp < -10) c.status = "CRITICAL";
        else if (temp < 0) c.status = "LOW";
        else c.status = "NORMAL";

        return c;
    }
}