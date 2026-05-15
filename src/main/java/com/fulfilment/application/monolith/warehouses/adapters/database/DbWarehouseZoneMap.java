package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_zone_map")
public class DbWarehouseZoneMap {

    @Id @GeneratedValue
    public Long id;

    public String zone;
    public Integer riskScore;
    public String level;

    public static DbWarehouseZoneMap create(String zone, Integer score) {
        DbWarehouseZoneMap z = new DbWarehouseZoneMap();
        z.zone = zone;
        z.riskScore = score;

        if (score == null) z.level = "UNKNOWN";
        else if (score > 80) z.level = "DANGER";
        else if (score > 50) z.level = "WARNING";
        else z.level = "SAFE";

        return z;
    }
}