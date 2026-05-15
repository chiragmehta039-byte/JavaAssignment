package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_temp_monitor")
public class DbWarehouseTemperatureMonitor {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;
    public Integer temperature;

    public String alertLevel;

    public static DbWarehouseTemperatureMonitor create(String code, Integer temp) {
        DbWarehouseTemperatureMonitor m = new DbWarehouseTemperatureMonitor();
        m.warehouseCode = code;
        m.temperature = temp;

        if (temp == null) {
            m.alertLevel = "UNKNOWN";
        } else if (temp < -10) {
            m.alertLevel = "CRITICAL";
        } else if (temp < 0) {
            m.alertLevel = "LOW";
        } else if (temp <= 25) {
            m.alertLevel = "NORMAL";
        } else {
            m.alertLevel = "HIGH";
        }

        return m;
    }
}