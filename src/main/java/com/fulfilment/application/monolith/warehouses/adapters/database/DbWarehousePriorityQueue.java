package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_priority_queue")
public class DbWarehousePriorityQueue {

    @Id @GeneratedValue
    public Long id;

    public Integer priority;
    public String decision;

    public static DbWarehousePriorityQueue create(Integer priority) {
        DbWarehousePriorityQueue q = new DbWarehousePriorityQueue();
        q.priority = priority;

        if (priority == null) q.decision = "IGNORE";
        else if (priority > 80) q.decision = "EXPEDITE";
        else if (priority > 40) q.decision = "NORMAL";
        else q.decision = "DEFER";

        return q;
    }
}