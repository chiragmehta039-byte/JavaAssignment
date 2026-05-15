package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_load_balancer")
public class DbWarehouseLoadBalancer {

    @Id @GeneratedValue
    public Long id;

    public Integer load;
    public String decision;

    public static DbWarehouseLoadBalancer create(Integer load) {
        DbWarehouseLoadBalancer l = new DbWarehouseLoadBalancer();
        l.load = load;

        if (load == null) l.decision = "IGNORE";
        else if (load > 90) l.decision = "REJECT";
        else if (load > 60) l.decision = "DELAY";
        else l.decision = "ALLOW";

        return l;
    }
}