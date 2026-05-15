package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_alert_engine")
public class DbWarehouseAlertEngine {

    @Id @GeneratedValue
    public Long id;

    public String warehouseCode;

    public Boolean overstock;

    public Boolean understock;

    public String alertLevel;

    public static DbWarehouseAlertEngine evaluate(String code, Boolean over, Boolean under) {

        DbWarehouseAlertEngine a = new DbWarehouseAlertEngine();

        a.warehouseCode = code;
        a.overstock = over;
        a.understock = under;

        if (code == null) {
            a.alertLevel = "INVALID";
        } else if (Boolean.TRUE.equals(over)) {
            a.alertLevel = "OVERSTOCK";
        } else if (Boolean.TRUE.equals(under)) {
            a.alertLevel = "UNDERSTOCK";
        } else {
            a.alertLevel = "OK";
        }

        return a;
    }
}