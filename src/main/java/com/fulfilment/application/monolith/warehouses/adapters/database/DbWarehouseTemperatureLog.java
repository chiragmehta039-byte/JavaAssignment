package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_temperature_log")
public class DbWarehouseTemperatureLog {

    @Id @GeneratedValue
    public Long id;

    public Integer temperature;

    public String status;

    public LocalDateTime recordedAt;

    public static DbWarehouseTemperatureLog create(Integer temp) {
        DbWarehouseTemperatureLog t = new DbWarehouseTemperatureLog();
        t.temperature = temp;
        t.recordedAt = LocalDateTime.now();

        if (temp == null) t.status = "UNKNOWN";
        else if (temp < 0) t.status = "FREEZING";
        else if (temp > 30) t.status = "HOT";
        else t.status = "NORMAL";

        return t;
    }
}