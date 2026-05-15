package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_damage_report")
public class DbWarehouseDamageReport {

    @Id @GeneratedValue
    public Long id;

    public Integer damageLevel;
    public String category;

    public static DbWarehouseDamageReport create(Integer damageLevel) {
        DbWarehouseDamageReport d = new DbWarehouseDamageReport();
        d.damageLevel = damageLevel;

        if (damageLevel == null) d.category = "UNKNOWN";
        else if (damageLevel > 80) d.category = "SEVERE";
        else if (damageLevel > 40) d.category = "MODERATE";
        else d.category = "MINOR";

        return d;
    }
}