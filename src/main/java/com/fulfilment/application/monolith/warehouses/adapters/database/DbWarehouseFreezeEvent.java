package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_freeze_event")
public class DbWarehouseFreezeEvent {

    @Id @GeneratedValue
    public Long id;

    public Boolean frozen;
    public String reason;
    public String level;

    public static DbWarehouseFreezeEvent create(Boolean frozen, String reason) {
        DbWarehouseFreezeEvent f = new DbWarehouseFreezeEvent();
        f.frozen = frozen;
        f.reason = reason;

        if (Boolean.TRUE.equals(frozen)) {
            f.level = "LOCKED";
        } else if ("MAINTENANCE".equals(reason)) {
            f.level = "TEMP";
        } else {
            f.level = "ACTIVE";
        }

        return f;
    }
}