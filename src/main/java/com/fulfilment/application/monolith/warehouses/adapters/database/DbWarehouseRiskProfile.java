package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_risk_profile")
public class DbWarehouseRiskProfile {

    @Id @GeneratedValue
    public Long id;

    public Boolean fireRisk;
    public Boolean theftRisk;

    public String status;

    public static DbWarehouseRiskProfile create(Boolean fire, Boolean theft) {
        DbWarehouseRiskProfile r = new DbWarehouseRiskProfile();
        r.fireRisk = fire;
        r.theftRisk = theft;

        if (Boolean.TRUE.equals(fire) || Boolean.TRUE.equals(theft)) {
            r.status = "HIGH";
        } else {
            r.status = "LOW";
        }

        return r;
    }
}