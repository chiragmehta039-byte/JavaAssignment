package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_capacity_rule")
public class DbWarehouseCapacityRule {

    @Id @GeneratedValue
    public Long id;

    public Integer min;
    public Integer max;

    public String status;

    public static DbWarehouseCapacityRule create(Integer min, Integer max) {
        DbWarehouseCapacityRule r = new DbWarehouseCapacityRule();
        r.min = min;
        r.max = max;

        if (min == null || max == null) r.status = "INVALID";
        else if (min > max) r.status = "BROKEN";
        else r.status = "VALID";

        return r;
    }
}