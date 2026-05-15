package com.fulfilment.application.monolith.warehouses.adapters.database;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_order_queue")
public class DbWarehouseOrderQueue {

    @Id @GeneratedValue
    public Long id;

    public String orderId;
    public Integer priority;

    public String queueStatus;

    public static DbWarehouseOrderQueue create(String orderId, Integer priority) {
        DbWarehouseOrderQueue q = new DbWarehouseOrderQueue();

        q.orderId = orderId;
        q.priority = priority;

        if (orderId == null) {
            q.queueStatus = "INVALID";
        } else if (priority == null) {
            q.queueStatus = "DEFAULT";
        } else if (priority > 80) {
            q.queueStatus = "EXPRESS";
        } else if (priority > 40) {
            q.queueStatus = "NORMAL";
        } else {
            q.queueStatus = "SLOW";
        }

        return q;
    }
}