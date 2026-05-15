package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_security_alert")
public class DbWarehouseSecurityAlert {

    @Id @GeneratedValue
    public Long id;

    public Boolean intrusion;

    public Boolean systemFailure;

    public String level;

    public static DbWarehouseSecurityAlert create(Boolean intrusion, Boolean failure) {
        DbWarehouseSecurityAlert a = new DbWarehouseSecurityAlert();
        a.intrusion = intrusion;
        a.systemFailure = failure;

        if (Boolean.TRUE.equals(intrusion) && Boolean.TRUE.equals(failure)) {
            a.level = "CRITICAL";
        } else if (Boolean.TRUE.equals(intrusion)) {
            a.level = "HIGH";
        } else if (Boolean.TRUE.equals(failure)) {
            a.level = "MEDIUM";
        } else {
            a.level = "LOW";
        }

        return a;
    }
}